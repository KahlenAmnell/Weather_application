package pl.bernat.model;

import pl.bernat.model.client.WeatherClient;

import java.util.List;

public class WeatherService {
    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient){
        this.weatherClient = weatherClient;
    }

    public Weather getWeather(String cityName) {
        return weatherClient.getWeather(cityName);
    }

    public List<Forecast> getForecasts(){
        return weatherClient.getForecasts();
    }
}
