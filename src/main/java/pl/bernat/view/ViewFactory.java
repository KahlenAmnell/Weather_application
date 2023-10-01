package pl.bernat.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.bernat.controller.BaseController;
import pl.bernat.controller.MainWindowController;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {
    private ArrayList<Stage> activeStages;
    public ViewFactory() {
        activeStages = new ArrayList<Stage>();
    }

    public void showMainWindow(){
        BaseController controller = new MainWindowController(this, "/FXMLFiles/MainWindow.fxml");
        initializeStage(controller);
    }
    private void initializeStage(BaseController baseController){
        FXMLLoader window = setFxmlLoader(baseController);

        Parent parent;
        try {
            parent = window.load();
        } catch (IOException e){
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }
    private FXMLLoader setFxmlLoader(BaseController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        loader.setController(controller);
        return loader;
    }
}
