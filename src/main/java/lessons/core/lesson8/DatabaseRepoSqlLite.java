package lessons.core.lesson8;

import lessons.core.lesson8.interfaces.DatabaseRepository;
import lessons.core.lesson8.sabclass.WeatherData;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepoSqlLite implements DatabaseRepository {

    static {
        try{
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    String filename = null;
    String createTableQuery = "CREATE TABLE IF NOT EXISTS weather (\nid INTEGER PRIMARY KEY AUTOINCREMENT," +
            "\ncity TEXT NOT NULL,\ndate_time TEXT NOT NULL,\nweather_text TEXT NOT NULL," +
            "\ntemperature INTEGER NOT NULL\n);";

    String insertWeatherQuery = "INSERT INTO weather (city, date_time, weather_text, temperature) VALUES";

    public DatabaseRepoSqlLite () {
        filename = AppGlobalState.getInstance().getFileDb();
        createTableIfNotExists();
    }

    public Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
        return connection;
    }

    private void createTableIfNotExists() {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveWeatherData(ArrayList<WeatherData> array) throws SQLException {
        for(int i = 0; i < array.size(); i++) {
            try {
                Connection conn = getConnection();
                Statement st = conn.createStatement();
                st.executeUpdate(insertWeatherQuery + " ('" + array.get(i).getCity() + "', '" +
                        array.get(i).getDate() + "', '" + array.get(i).getWeatherType() +
                        "', " + array.get(i).getTemperature() + ");");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<WeatherData> getAllSaveData() throws IOException {
        return null;
    }
}
