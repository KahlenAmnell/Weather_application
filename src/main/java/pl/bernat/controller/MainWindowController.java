package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import pl.bernat.view.ViewFactory;

public class MainWindowController extends BaseController{
    @FXML
    private Label closeLabel;

    @FXML
    private HBox forecastHBox;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void closeAction() {

    }
}
