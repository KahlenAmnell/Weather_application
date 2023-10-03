package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.bernat.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
    @FXML
    private Label closeLabel;

    @FXML
    private HBox rightForecast, leftForecast;

    @FXML
    private ForecastController rightForecastController, leftForecastController;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);

    }

    @FXML
    void closeAction() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
