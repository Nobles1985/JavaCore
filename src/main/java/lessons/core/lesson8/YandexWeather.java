package lessons.core.lesson8;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lessons.core.lesson8.enums.Periods;
import lessons.core.lesson8.interfaces.WeatherProvider;
import lessons.core.lesson8.sabclass.WeatherData;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class YandexWeather implements WeatherProvider {

    private static final String HOST = "api.weather.yandex.ru";
    private static final String API_VERSION = "v2";
    private static final String FORECAST_ENDPOINT = "forecast";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private DatabaseRepoSqlLite baseData = new DatabaseRepoSqlLite();

    public ArrayList<WeatherData> getWeather(Periods periods) throws IOException {
        ArrayList<WeatherData> data = new ArrayList<>();
        String city = detectCity();
        String[] cityChoice = city.split(" ");
        if(periods.equals(Periods.ONE_DAY)){
            HttpUrl url = new HttpUrl
                    .Builder()
                    .scheme("https")
                    .host(HOST)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(FORECAST_ENDPOINT)
                    .addQueryParameter("lat", cityChoice[1])
                    .addQueryParameter("lon", cityChoice[0])
                    .addQueryParameter("lang", "ru_RU")
                    .addQueryParameter("limit", "1")
                    .addQueryParameter("hours", "false")
                    .addQueryParameter("extra", "false")
                    .build();

            Request request = new Request
                    .Builder()
                    .addHeader("accept","application/json")
                    .url(url)
                    .addHeader("X-Yandex-API-Key", AppGlobalState.getInstance().getApiKey())
                    .build();

            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()) {
                throw new IOException("Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
            }

            String jsonResponse = response.body().string();

            if (objectMapper.readTree(jsonResponse).size() > 0) {
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);
                String town = jsonNode.get("geo_object").get("locality").get("name").asText();
                String date = jsonNode.get("forecasts").get(0).get("date").asText();
                String info = jsonNode.get("forecasts").get(0).get("parts").get("day_short").get("condition").asText();
                String temperature = jsonNode.get("forecasts").get(0).get("parts").get("day_short").get("temp").asText();
                String[] dateIs = date.split("-");
                System.out.println("В городе " + town + " на " + dateIs[2] + "." + dateIs[1] + "." + dateIs[0] +
                                " ожидается погода: " + weatherType(info) + " , температура: " + temperature + " градусов.");
                data.add(new WeatherData(town, dateIs[2] + "." + dateIs[1] + "." + dateIs[0], weatherType(info), Double.valueOf(temperature)));
                try{
                    baseData.saveWeatherData(data);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else throw new IOException("Сервер не нашел указанный город");

        } else if(periods.equals(Periods.FIVE_DAYS)){
            HttpUrl url = new HttpUrl
                    .Builder()
                    .scheme("https")
                    .host(HOST)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(FORECAST_ENDPOINT)
                    .addQueryParameter("lat", cityChoice[1])
                    .addQueryParameter("lon", cityChoice[0])
                    .addQueryParameter("lang", "ru_RU")
                    .addQueryParameter("limit", "5")
                    .addQueryParameter("hours", "false")
                    .addQueryParameter("extra", "false")
                    .build();

            Request request = new Request
                    .Builder()
                    .addHeader("accept","application/json")
                    .url(url)
                    .addHeader("X-Yandex-API-Key", AppGlobalState.getInstance().getApiKey())
                    .build();

            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()) {
                throw new IOException("Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
            }

            String jsonResponse = response.body().string();

            if (objectMapper.readTree(jsonResponse).size() > 0) {
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);
                for(int i = 0; i < 5; i++){
                    String town = jsonNode.get("geo_object").get("locality").get("name").asText();
                    String date = jsonNode.get("forecasts").get(i).get("date").asText();
                    String info = jsonNode.get("forecasts").get(i).get("parts").get("day_short").get("condition").asText();
                    String temperature = jsonNode.get("forecasts").get(i).get("parts").get("day_short").get("temp").asText();
                    String[] dateIs = date.split("-");
                    System.out.println("В городе " + town + " на дату " + dateIs[2] + "." + dateIs[1] + "." + dateIs[0] +
                        " ожидается погода: " + weatherType(info) + " , температура: " + temperature + " градусов.");
                    data.add(new WeatherData(town, dateIs[2] + "." + dateIs[1] + "." + dateIs[0], weatherType(info), Double.valueOf(temperature)));
                }
                try{
                    baseData.saveWeatherData(data);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else throw new IOException("Server returns 0 cities");
        }
        return data;
    }

    public void getAllFromDb() throws IOException {
        try{
            for(int i = 0; i < baseData.getAllSaveData().size(); i++){
                System.out.println(baseData.getAllSaveData().get(i).toGetWeather());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String detectCity() throws IOException{
        String city = AppGlobalState.getInstance().getSelectCity();
        HttpUrl cityCoordinates = new HttpUrl
                .Builder()
                .scheme("https")
                .host("geocode-maps.yandex.ru")
                .addPathSegment("1.x")
                .addQueryParameter("apikey","043ec9df-ebc5-4df9-a7e5-07d478b9a682")
                .addQueryParameter("geocode", city)
                .addQueryParameter("format", "json")
                .build();

        Request request = new Request
                .Builder()
                .addHeader("accept","application/json")
                .url(cityCoordinates)
                .build();

        Response response = client.newCall(request).execute();

        if(!response.isSuccessful()) {
            throw new IOException("Невозможно прочесть информацию о городе. " +
                    "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("Произвожу поиск города " + city);
        String coordinate;

        if (objectMapper.readTree(jsonResponse).size() > 0) {
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            String cityName = jsonNode.get("response").get("GeoObjectCollection").get("featureMember").get(0).get("GeoObject").get("name").asText();
            String countryName = jsonNode.get("response").get("GeoObjectCollection").get("featureMember").get(0).get("GeoObject").get("description").asText();
            System.out.println("Найден город " + cityName + " в стране " + countryName);
            coordinate = jsonNode.get("response").get("GeoObjectCollection").get("featureMember").get(0).get("GeoObject").get("Point").get("pos").asText();
        } else throw new IOException("Server returns 0 cities");
        return coordinate;
    }

    public String weatherType(String key){

        HashMap<String, String> weatherType = new HashMap<String, String>();

        weatherType.put("clear", "ясно");
        weatherType.put("partly-cloudy", "малооблачно");
        weatherType.put("cloudy", "облачно с прояснениями");
        weatherType.put("overcast", "пасмурно");
        weatherType.put("drizzle", "морось");
        weatherType.put("light-rain", "небольшой дождь");
        weatherType.put("rain", "дождь");
        weatherType.put("moderate-rain", "умеренно сильный дождь");
        weatherType.put("heavy-rain", "сильный дождь");
        weatherType.put("continuous-heavy-rain", "длительный сильный дождь");
        weatherType.put("showers", "ливень");
        weatherType.put("wet-snow", "дождь со снегом");
        weatherType.put("light-snow", "небольшой снег");
        weatherType.put("snow", "снег");
        weatherType.put("snow-showers", "снегопад");
        weatherType.put("hail", "град");
        weatherType.put("thunderstorm", "гроза");
        weatherType.put("thunderstorm-with-rain", "дождь с грозой");
        weatherType.put("thunderstorm-with-hail", "гроза с градом");

        String value = weatherType.get(key);
        return value;
    }
}
