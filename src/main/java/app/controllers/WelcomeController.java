package app.controllers;

import app.services.SceneBuilder;
import app.services.SqlCaller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable, Controller {

    @FXML
    private Button startQuizButton;
    public SceneBuilder sb;

    public void startQuiz() throws Exception{
        sb.setNewScene((Stage)startQuizButton.getScene().getWindow(), "quizSelection");

    }

    public void testDBConnection() {
        SqlCaller.getAllQuestions();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sb = new SceneBuilder();
    }

    @Override
    public void initData(Object parameter) {

    }
}

