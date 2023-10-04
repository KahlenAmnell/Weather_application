package pl.bernat.model;

public class Weather {
    String responseCod;
    String date;
    double temperature;
    int pressure;
    int humidity;
    String weatherDescription;
    String weatherIcon;
    int cloudiness;
    double windSpeed;
    String cityName;

    public Weather(String responseCod, String date, double temperature, int pressure, int humidity, String weatherDescription, String weatherIcon, int cloudiness, double windSpeed, String cityName) {
        this.responseCod = responseCod;
        this.date = date;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.cloudiness = cloudiness;
        this.windSpeed = windSpeed;
        this.cityName = cityName;
    }

    public String getResponseCod() {
        return responseCod;
    }

    public String getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getCityName() {
        return cityName;
    }
}
