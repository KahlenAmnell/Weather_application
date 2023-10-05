package pl.bernat.model;

import pl.bernat.model.client.OpenWeatherMapClient;
import pl.bernat.model.client.WeatherClient;

public class WeatherServiceFactory {
    public static WeatherService createWeatherService(){
        return new WeatherService(createWeatherClient());
    }
    private static WeatherClient createWeatherClient(){
        return new OpenWeatherMapClient();
    }
}
