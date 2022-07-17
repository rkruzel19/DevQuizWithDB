package app.controllers;

import app.dao.Question;
import app.services.InputHandler;
import app.services.QuizQuestionsGenerator;
import app.services.SceneBuilder;
import app.services.SqlCaller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuizSelectionController implements Initializable, Controller {

    @FXML
    public List<Question> quizQuestions;
    @FXML
    public Button beginQuizButton;
    @FXML
    public Button homeButton;
    @FXML
    public TextField numberOfQuestions;
    @FXML
    public Label totalQsInDatabase;
    @FXML
    public Label errorLabel;
    SceneBuilder sb;
    int amount = 0;


    public void beginQuiz() throws Exception{
        verifyInput();
        if(amount > 0){
            populateQuizQuestions(amount);
            sb.setNewSceneWithParameters((Stage) beginQuizButton.getScene().getWindow(), "quiz", quizQuestions);
        }
    }

    public void returnHome() throws Exception{
        sb.setNewScene((Stage)homeButton.getScene().getWindow(), "welcome");
    }

    public int receiveInput(){
        return Integer.parseInt(numberOfQuestions.getText());
    }

    public void verifyInput(){
        if(InputHandler.verifyNumberOfQs(numberOfQuestions.getText())){
            amount = receiveInput();
        } else {
            errorLabel.setText("Must enter valid input (1-" + SqlCaller.getDBSize() + ") and click verify input");
            amount = 0;
            errorLabel.setTextFill(Color.RED);
        }
    }

    public void populateQuizQuestions(int numberOfQuestions){
        quizQuestions = QuizQuestionsGenerator.generateXQuestions(numberOfQuestions);
    }

    public void beginQuizAllQuestions() throws Exception{
        quizQuestions = QuizQuestionsGenerator.generateEntireQuestionList();
        sb.setNewSceneWithParameters((Stage)beginQuizButton.getScene().getWindow(), "quiz", quizQuestions);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sb = new SceneBuilder();
        quizQuestions = new ArrayList<>();
        totalQsInDatabase.setText(SqlCaller.getDBSize() + " questions available");
    }

    @Override
    public void initData(Object parameter) {

    }
}
