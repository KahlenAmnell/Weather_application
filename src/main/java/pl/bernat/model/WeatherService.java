package pl.bernat.model;

import pl.bernat.model.client.WeatherClient;

import java.util.List;

public class WeatherService {
    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient){
        this.weatherClient = weatherClient;
    }

    public Weather downloadWeather(String cityName) {
        return weatherClient.downloadWeather(cityName);
    }

    public List<Forecast> downloadForecasts(){
        return weatherClient.downloadForecasts();
    }
}
