package lessons.core.lesson7;

import lessons.core.lesson7.enums.Periods;

import java.io.IOException;

public interface WeatherProvider {
    void getWeather(Periods periods) throws IOException;
}
