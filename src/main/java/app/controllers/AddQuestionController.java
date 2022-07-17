package app.controllers;

import app.dao.Question;
import app.services.SceneBuilder;
import app.services.SqlCaller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.*;

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
        Collections.addAll(choices, answerString1.getText(), answerString2.getText(),
                answerString3.getText(), answerString4.getText());
        SqlCaller.addQuestionToDB(questionId, question, correctAnswer, choices);
        clearSelections();
        confirmSubmission();
    }
    public void confirmSubmission(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Question Submitted");
        alert.setHeaderText("Submitted");
        alert.setContentText("Your question has been submitted to the database.");
        alert.showAndWait();
    }
    public void clearSelections(){
        List<TextField> textFields = new ArrayList<>(Arrays.asList(questionString, answerString1, answerString2,
                answerString3, answerString4));
        for (TextField tf : textFields){
            tf.clear();
        }
        correctAnswerChoice.selectToggle(null);
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
