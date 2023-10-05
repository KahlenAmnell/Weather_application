package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import pl.bernat.model.Weather;
import pl.bernat.model.WeatherService;
import pl.bernat.model.WeatherServiceFactory;

public class ForecastController {
    @FXML
    private TextField changeCityTextField;

    @FXML
    private VBox changeCityVBox;

    @FXML
    private Label cityNameLabel;

    @FXML
    private Label cloudinessLabel;

    @FXML
    private Label date1Label;

    @FXML
    private Label date2Label;

    @FXML
    private Label date3Label;

    @FXML
    private Label date4Label;

    @FXML
    private Label dateLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Label humidityLabel;

    @FXML
    private ImageView icon1;

    @FXML
    private ImageView icon2;

    @FXML
    private ImageView icon3;

    @FXML
    private ImageView icon4;

    @FXML
    private Label pressureLabel;

    @FXML
    private Label temperature1Label;

    @FXML
    private Label temperature2Label;

    @FXML
    private Label temperature3Label;

    @FXML
    private Label temperature4Label;

    @FXML
    private Label temperatureLabel;

    @FXML
    private ImageView weatherIcon;

    @FXML
    private Label windSpeedLabel;
    private WeatherService weatherService = WeatherServiceFactory.createWeatherService();

    @FXML
    void disableChangeCityVBox() {

    }

    @FXML
    void refreshForecast() {
        getWeather();
    }

    @FXML
    void saveCityAction() {
        cityNameLabel.setText(changeCityTextField.getText());
        getWeather();
        closeChangeCityVbox();
    }

    void getWeather(){
        Weather weather = weatherService.getWeather(cityNameLabel.getText());
        displayCurrentWeather(weather);
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
