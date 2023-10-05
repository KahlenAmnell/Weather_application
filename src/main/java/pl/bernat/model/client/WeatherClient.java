package pl.bernat.model.client;

import pl.bernat.model.Forecast;
import pl.bernat.model.Weather;

import java.util.List;

public interface WeatherClient {
    Weather getWeather(String cityName);

    List<Forecast> getForecasts();
}
