package lessons.core.lesson8.sabclass;

public class WeatherData {

    private String city;
    private String date;
    private String weatherType;
    private Integer temperature;

    public WeatherData(){
    }

    public WeatherData(String city, String date, String weatherType, Integer temperature){
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

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }
}
