package lessons.core.lesson8;

import lessons.core.lesson8.interfaces.DatabaseRepository;
import lessons.core.lesson8.sabclass.WeatherData;

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
            "\ncity TEXT NOT NULL,\ndate TEXT NOT NULL,\nweather_type TEXT NOT NULL," +
            "\ntemperature REAL NOT NULL\n);";

    String insertWeatherQuery = "INSERT INTO weather (city, date, weather_type, temperature) VALUES (?, ?, ?, ?)";

    String getWeatherQuery = "SELECT * FROM weather";

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

    public void saveWeatherData(ArrayList<WeatherData> array) throws SQLException{
        try (Connection conn = getConnection()){
            for (int i = 0; i < array.size(); i++){
                PreparedStatement preparedStatement = conn.prepareStatement(insertWeatherQuery);
                preparedStatement.setString(1, array.get(i).getCity());
                preparedStatement.setString(2, array.get(i).getDate());
                preparedStatement.setString(3, array.get(i).getWeatherType());
                preparedStatement.setDouble(4, array.get(i).getTemperature());
                preparedStatement.executeUpdate();
            }
        }
    }

    public List<WeatherData> getAllSaveData() throws SQLException{
        List<WeatherData> data = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(getWeatherQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            String city = resultSet.getString("city");
            String date = resultSet.getString("date");
            String weatherType = resultSet.getString("weather_type");
            Double temperature = resultSet.getDouble("temperature");
            data.add(new WeatherData(city,date, weatherType, temperature));
        }
        return data;
    }
}
