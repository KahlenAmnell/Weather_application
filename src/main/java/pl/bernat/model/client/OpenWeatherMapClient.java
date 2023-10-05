package pl.bernat.model.client;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import pl.bernat.model.Weather;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenWeatherMapClient implements WeatherClient{
    private final String API_ID = Config.API_ID;
    private final String UNITS = "metric";
    private final String LANG = "pl";
    private final Gson gson = new Gson();
    String result;
    @Override
    public Weather getWeather(String cityName) {
        cityName = replaceSpaceWithPlus(cityName);

        String request = "https://api.openweathermap.org/data/2.5/forecast?appid=" + API_ID + "&units=" + UNITS + "&lang=" + LANG + "&q=" + cityName;

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(request))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            result = response.body();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return setWeather();
    }

    private Weather setWeather() {
        JsonPrimitive cod = gson.fromJson(result, JsonObject.class).getAsJsonPrimitive("cod");
        JsonArray list = gson.fromJson(result, JsonObject.class).getAsJsonArray("list");
        JsonObject main = list.get(0).getAsJsonObject().get("main").getAsJsonObject();
        JsonArray weather = list.get(0).getAsJsonObject().get("weather").getAsJsonArray();
        JsonObject clouds = list.get(0).getAsJsonObject().get("clouds").getAsJsonObject();
        JsonObject wind = list.get(0).getAsJsonObject().get("wind").getAsJsonObject();
        JsonPrimitive date = list.get(0).getAsJsonObject().get("dt_txt").getAsJsonPrimitive();
        JsonObject city =  gson.fromJson(result, JsonObject.class).getAsJsonObject("city");

        double temperature = (int) Math.round(main.get("temp").getAsDouble());
        int pressure = main.get("pressure").getAsInt();
        int humidity = main.get("humidity").getAsInt();
        String weatherDescription = weather.get(0).getAsJsonObject().get("description").getAsString();
        String weatherIcon = weather.get(0).getAsJsonObject().get("icon").getAsString();
        int cloudiness = clouds.get("all").getAsInt();
        double windSpeed = wind.get("speed").getAsDouble();
        String cityName = city.get("name").getAsString();

        return new Weather(
                cod.getAsString(),
                date.getAsString(),
                temperature,
                pressure,
                humidity,
                weatherDescription,
                weatherIcon,
                cloudiness,
                windSpeed,
                cityName
        );
    }

    private String replaceSpaceWithPlus(String cityName) {
            return cityName.replace(" ", "+");
        }
}
