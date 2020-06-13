package app.controllers;

import app.dao.Question;
import app.services.SceneBuilder;
import app.services.SqlCaller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable, Controller {

    @FXML
    TextField questionString;
    @FXML
    Button cancelButton;
    @FXML
    Button submitQuestionButton;
    @FXML
    ToggleGroup correctAnswerChoice;
    @FXML
    RadioButton answer1;
    @FXML
    RadioButton answer2;
    @FXML
    RadioButton answer3;
    @FXML
    RadioButton answer4;
    @FXML
    TextField answerString1;
    @FXML
    TextField answerString2;
    @FXML
    TextField answerString3;
    @FXML
    TextField answerString4;

    SceneBuilder sb;

    public void backToHome() throws Exception {
        sb.setNewScene((Stage)cancelButton.getScene().getWindow(), "welcome");
    }

    public void submitQuestionToDB(){
        int questionId = SqlCaller.getDBSize() + 1;
        String question = questionString.getText();
        String correctAnswer = getCorrectAnswer();
        List<String> choices = new ArrayList<>();
        choices.add(answerString1.getText());
        choices.add(answerString2.getText());
        choices.add(answerString3.getText());
        choices.add(answerString4.getText());
//        Question newQuestion = new Question(questionId, question, choices, correctAnswer);
        SqlCaller.addQuestionToDB(questionId, question, correctAnswer, choices);
        System.out.println("Id = " + questionId + "\nQuestion = " + question + "\nCorrect answer = " + correctAnswer + "\nChoices = " + choices);
    }

    public String getCorrectAnswer(){
        RadioButton selectedRadio = (RadioButton)correctAnswerChoice.getSelectedToggle();
        String radioId = selectedRadio.getId();
        switch (radioId){
            case "answer1": return answerString1.getText();
            case "answer2": return answerString2.getText();
            case "answer3": return answerString3.getText();
            case "answer4": return answerString4.getText();
            default: return "";
        }
    }

    @Override
    public void initData(Object parameter) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sb = new SceneBuilder();
    }
}
