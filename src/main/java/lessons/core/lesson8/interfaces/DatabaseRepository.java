package lessons.core.lesson8.interfaces;

import lessons.core.lesson8.sabclass.WeatherData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DatabaseRepository {

    void saveWeatherData(ArrayList<WeatherData> weatherData) throws SQLException;
    List<WeatherData> getAllSaveData() throws IOException;
}
