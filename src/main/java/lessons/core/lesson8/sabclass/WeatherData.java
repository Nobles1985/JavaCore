package lessons.core.lesson8.sabclass;

public class WeatherData {

    private String city;
    private String date;
    private String weatherType;
    private Double temperature;

    public WeatherData(){
    }

    public WeatherData(String city, String date, String weatherType, Double temperature){
        this.city = city;
        this.date = date;
        this.weatherType = weatherType;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String toGetWeather (){
        String str = city + " " + date + " " + weatherType + " " + Double.toString(temperature) + " градусов";
        return str;
    }
}
