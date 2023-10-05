package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import pl.bernat.model.Forecast;
import pl.bernat.model.Weather;
import pl.bernat.model.WeatherService;
import pl.bernat.model.WeatherServiceFactory;

import java.net.URL;
import java.util.*;

public class ForecastController implements Initializable {

    //Vbox to change city variables
    @FXML
    private TextField changeCityTextField;

    @FXML
    private VBox changeCityVBox;

    @FXML
    private Label errorLabel;

    //Current weather variables
    @FXML
    private Label cityNameLabel;

    @FXML
    private Label cloudinessLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label pressureLabel;

    @FXML
    private Label temperatureLabel;

    @FXML
    private ImageView weatherIcon;

    @FXML
    private Label windSpeedLabel;

    //Forecasts variables
    private final List<Label> forecastDates = new ArrayList<>();
    @FXML
    private Label date1Label, date2Label, date3Label, date4Label;

    private final List<ImageView> forecastImages = new ArrayList<>();
    @FXML
    private ImageView icon1, icon2, icon3, icon4;

    private final List<Label> forecastTemperatures = new ArrayList<>();

    @FXML
    private Label temperature1Label, temperature2Label, temperature3Label, temperature4Label;

    private WeatherService weatherService = WeatherServiceFactory.createWeatherService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Collections.addAll(forecastDates, date1Label, date2Label, date3Label, date4Label);
        Collections.addAll(forecastImages, icon1, icon2, icon3, icon4);
        Collections.addAll(forecastTemperatures, temperature1Label, temperature2Label, temperature3Label, temperature4Label);
    }

    public void setInitiatingWeather(String cityName) {
        cityNameLabel.setText(cityName);
        getWeather();
    }
    @FXML
    void disableChangeCityVBox() {
        closeChangeCityVbox();
    }

    @FXML
    void refreshForecast() {
        getWeather();
    }

    @FXML
    void saveCityAction() {
        cityNameLabel.setText(changeCityTextField.getText());
        if(getWeather()) {
            errorLabel.setVisible(false);
            closeChangeCityVbox();
        } else {
            errorLabel.setVisible(true);
        }
    }

    boolean getWeather(){
        Weather weather = weatherService.getWeather(cityNameLabel.getText());
        if(weather != null) {
            displayCurrentWeather(weather);
            List<Forecast> forecasts = weatherService.getForecasts();
            displayForecasts(forecasts);
            return true;
        }
        return false;
    }

    private void displayCurrentWeather(Weather weather) {
        cityNameLabel.setText(weather.getCityName());
        dateLabel.setText(weather.getDate());
        weatherIcon.setImage(new Image("http://openweathermap.org/img/w/" + weather.getWeatherIcon() + ".png"));
        temperatureLabel.setText(weather.getTemperature() + "°C");
        humidityLabel.setText("Wilgotność: " + weather.getHumidity() + "%");
        windSpeedLabel.setText("Wiatr: " + weather.getWindSpeed() + " m/s");
        pressureLabel.setText("Ciśnienie: " + weather.getPressure() + "hPa");
        cloudinessLabel.setText("Zachmurzenie: " + weather.getCloudiness() + "%");
        descriptionLabel.setText(weather.getWeatherDescription());
    }

    private void displayForecasts(List<Forecast> forecasts){
        int numberForecast = 0;
        for(Forecast forecast: forecasts){
            forecastDates.get(numberForecast).setText(forecast.getDate());
            forecastImages.get(numberForecast).setImage(new Image("http://openweathermap.org/img/w/" + forecast.getWeatherIcon() + ".png"));
            forecastTemperatures.get(numberForecast).setText(forecast.getTemperature() + "°C");
            numberForecast++;
        }
    }

    @FXML
    void showUpChangeCityVBox() {
        changeCityVBox.setVisible(true);
        changeCityVBox.setDisable(false);
    }

    void closeChangeCityVbox(){
        changeCityVBox.setVisible(false);
        changeCityVBox.setDisable(true);
    }


}
