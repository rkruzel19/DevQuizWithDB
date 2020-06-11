package app.controllers;

import app.services.SceneBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EndOfQuizController implements Initializable, Controller {

    @FXML
    Label userScore;
    @FXML
    Button homeButton;

    SceneBuilder sb;


    public void goHome() throws Exception{
        sb.setNewScene((Stage)homeButton.getScene().getWindow(), "welcome");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sb = new SceneBuilder();
    }

    @Override
    public void initData(Object parameter) {
        Label label = (Label) parameter;
        this.userScore.setText(label.getText());
    }
}