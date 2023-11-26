package pl.bernat.model.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import pl.bernat.model.Forecast;
import pl.bernat.model.Weather;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

class OpenWeatherMapClientTest {
    private static final String EXAMPLE_API_RESPONSE_FILE = "exampleApiResponseWeather.json";
    private static final String EXAMPLE_CITY_NOT_FOUND_RESPONSE_FILE = "exampleCityNotFoundResponse.json";
    private static final String API_ID = Config.API_ID;
    private static final String main = "/api.openweathermap.org/data/2.5/forecast?appid=";
    private static final String additional = "&units=metric&lang=pl&q=";
    private static final String cityName = "wroclaw";
    private static final String testUrl = main + API_ID + additional + cityName;
    private static final String LOCALHOST_URL = "http://localhost:8090";
    private static WireMockServer wireMockServer;


    @BeforeAll
    static void setup() {
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
    }

    @AfterAll
    static void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void givenStubShouldReturnStatusCode200() {
        generateExampleCorrectResponseStub();
        given().
        when().
            get(LOCALHOST_URL + testUrl).
        then().
                statusCode(200).
                assertThat().body("cod", is("200"));
    }

    @Test
    public void givenCityNameShouldReturnWeather() {
        generateExampleCorrectResponseStub();
        OpenWeatherMapClient weatherClient = new OpenWeatherMapClient();
        weatherClient.setUrl(LOCALHOST_URL);

        Weather result = weatherClient.downloadWeather(cityName);

        assertThat(result.getTemperature(), equalTo(8.0));
        assertThat(result.getCloudiness(), equalTo(83));
        assertThat(result.getWeatherDescription(), equalTo("słabe opady deszczu"));
        assertThat(result.getWeatherIcon(), equalTo("10d"));
    }

    @Test
    public void givenIncorrectCityNameShouldReturnNull() {
        generateNotFoundCityResponseStub();
        OpenWeatherMapClient weatherClient = new OpenWeatherMapClient();
        weatherClient.setUrl(LOCALHOST_URL);

        Weather result = weatherClient.downloadWeather(cityName);

        assertThat(result, equalTo(null));
    }

    @Test
    public void givenCityNameShouldReturnForecasts() {
        generateExampleCorrectResponseStub();
        OpenWeatherMapClient weatherClient = new OpenWeatherMapClient();
        weatherClient.setUrl(LOCALHOST_URL);
        weatherClient.downloadWeather(cityName);
        List<Forecast> result = weatherClient.downloadForecasts();

        assertThat(result.size(), equalTo(4));
        assertThat(result.get(0).getTemperature(), equalTo(1.0));
        assertThat(result.get(1).getDate(), equalTo("Czwartek"));
        assertThat(result.get(2).getTemperature(), equalTo(4.0));
        assertThat(result.get(3).getWeatherIcon(), equalTo("13d"));
    }



    private static void generateExampleCorrectResponseStub(){
        wireMockServer.stubFor(get(urlEqualTo(testUrl))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/" + EXAMPLE_API_RESPONSE_FILE)));
    }

    private static void generateNotFoundCityResponseStub(){
        wireMockServer.stubFor(get(urlEqualTo(testUrl))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/" + EXAMPLE_CITY_NOT_FOUND_RESPONSE_FILE)));
    }
}