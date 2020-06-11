package app;

import app.services.SceneBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SceneBuilder sb = new SceneBuilder();
        sb.setNewScene(primaryStage, "welcome");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
