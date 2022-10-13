package lessons.core.lesson6;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import java.io.IOException;

public class YandexWeatherRequest {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl
                .Builder()
                .scheme("https")
                .host("api.weather.yandex.ru")
                .addPathSegment("v2")
                .addPathSegment("forecast")
                //координаты города по широте и долготе, для которого нужно получить прогноз погоды
                .addQueryParameter("lat", "59.9386")
                .addQueryParameter("lon", "30.3141")
                //язык запроса
                .addQueryParameter("lang", "ru_RU")
                //на сколько дней запрос погоды, максимум 7
                .addQueryParameter("limit", "7")
                //запрос почасовой погоды
                .addQueryParameter("hours", "false")
                //запрос подробностей по осадкам
                .addQueryParameter("extra", "false")
                .build();

        System.out.println(url.toString());

        Request request = new Request
                .Builder()
                .addHeader("accept","application/json")
                .url(url)
                //ключ доступа к сервису яндекс.погода
                .addHeader("X-Yandex-API-Key", "8eff93e2-ccd1-44ce-87bd-4ef1c5e531ce")
                .build();

        String response = client.newCall(request).execute().body().string();
        System.out.println(response);
    }
}
