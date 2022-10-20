package lessons.core.lesson8;

import lessons.core.lesson8.enums.Functionality;
import lessons.core.lesson8.enums.Periods;
import lessons.core.lesson8.interfaces.WeatherProvider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    WeatherProvider weatherProvider = new YandexWeather();
    Map<Integer, Functionality> variantResult = new HashMap();

    public Controller(){
        variantResult.put(1, Functionality.GET_WEATHER_IN_ONE_DAY);
        variantResult.put(2, Functionality.GET_WEATHER_IN_FIVE_DAYS);
        variantResult.put(3, Functionality.GET_ALL_FROM_DB);
    }

    public void onUserInput(String input) throws IOException {
        int command = Integer.parseInt(input);
        if (!variantResult.containsKey(command)) {
            throw new IOException("Подобная команда отсутствует: " + command);
        }
        switch (variantResult.get(command)) {
            case GET_WEATHER_IN_ONE_DAY:
                getWeatherInOneDay();
                break;
            case GET_WEATHER_IN_FIVE_DAYS:
                getWeatherInFiveDays();
                break;
            case GET_ALL_FROM_DB:
                getWeatherFromDb();
                break;
        }
    }

    public void getWeatherInOneDay() throws IOException {
        weatherProvider.getWeather(Periods.ONE_DAY);
    }

    public void getWeatherInFiveDays() throws IOException {
        weatherProvider.getWeather(Periods.FIVE_DAYS);
    }

    private void getWeatherFromDb() throws IOException {
        weatherProvider.getAllFromDb();
    }
}
