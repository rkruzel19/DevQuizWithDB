package app.controllers;

import app.services.ConnectionUtil;
import app.services.SceneBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable, Controller {

    @FXML
    private Button startQuizButton;
    public SceneBuilder sb;

    public void startQuiz() throws Exception{
        sb.setNewScene((Stage)startQuizButton.getScene().getWindow(), "quizSelection");

    }

    Connection con = ConnectionUtil.conDB();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void testDBConnection() {
        String sql = "select * from question";

        try {
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("End of questions");
            } else {
                int questionId = Integer.parseInt(resultSet.getObject("id").toString());
                String question = resultSet.getObject("question").toString();
                String correctAnswer = resultSet.getObject("correctAnswer").toString();
                System.out.println("Question# " + questionId + "\nQuestion: " + question + "\nCorrect Answer: " + correctAnswer);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sb = new SceneBuilder();
    }

    @Override
    public void initData(Object parameter) {

    }
}

