package lessons.core.lesson7;

import lessons.core.lesson7.enums.Functionality;
import lessons.core.lesson7.enums.Periods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    WeatherProvider weatherProvider = new YandexWeather();
    Map<Integer, Functionality> variantResult = new HashMap();

    public Controller(){
        variantResult.put(1, Functionality.GET_WEATHER_IN_ONE_DAY);
        variantResult.put(2, Functionality.GET_WEATHER_IN_FIVE_DAYS);
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
        }
    }

    public void getWeatherInOneDay() throws IOException {
        weatherProvider.getWeather(Periods.ONE_DAY);
    }

    public void getWeatherInFiveDays() throws IOException {
        weatherProvider.getWeather(Periods.FIVE_DAYS);
    }
}
