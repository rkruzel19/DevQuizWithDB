package app.services;

import app.Main;
import app.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneBuilder<T> {

    public void setNewScene(Stage stage, String file) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/" + file + ".fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(Main.class.getResource("../css/" + file + ".css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void setNewSceneWithParameters(Stage stage, String file, T parameters) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/" + file + ".fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1200, 800);

        Controller controller = loader.getController();
        controller.initData(parameters);

        scene.getStylesheets().add(Main.class.getResource("../css/" + file + ".css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}

