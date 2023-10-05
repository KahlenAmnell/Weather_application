module Init{
    exports pl.bernat;

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens pl.bernat.controller to javafx.fxml, javafx.controls, com.google.gson;
    opens pl.bernat.view to javafx.fxml, javafx.controls, com.google.gson;
    opens pl.bernat.model to javafx.fxml, javafx.controls, com.google.gson;
    opens pl.bernat.model.client to com.google.gson;
    exports pl.bernat.model.client;
}
