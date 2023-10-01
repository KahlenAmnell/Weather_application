package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MainWindowConttroller extends BaseController{
    @FXML
    private Label closeLabel;

    @FXML
    private HBox forecastHBox;

    public MainWindowConttroller(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void closeAction() {

    }
}
