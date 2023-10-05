package pl.bernat.model;

public class Forecast {
    String date;
    double temperature;
    String weatherIcon;

    public Forecast(String date, double temperature, String weatherIcon) {
        this.date = date;
        this.temperature = temperature;
        this.weatherIcon = weatherIcon;
    }

    public String getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }
}
