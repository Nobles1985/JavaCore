package lessons.core.lesson8.interfaces;

import lessons.core.lesson8.enums.Periods;
import lessons.core.lesson8.sabclass.WeatherData;

import java.io.IOException;
import java.util.ArrayList;

public interface WeatherProvider {
    ArrayList<WeatherData> getWeather(Periods periods) throws IOException;
    void getAllFromDb() throws IOException;
}
