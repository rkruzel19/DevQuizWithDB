package app.controllers;

import app.dao.Category;
import app.dao.Question;
import app.services.InputHandler;
import app.services.QuizQuestionsGenerator;
import app.services.SceneBuilder;
import app.services.SqlCaller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
    public Label totalOfSelectedCategories;
    @FXML
    public Label errorLabel;
    @FXML
    public GridPane categories;
    SceneBuilder sb;
    int amount = 0;
    HashMap<String, Integer> QsInEachCategory = SqlCaller.getEachCategorySize();
    @FXML
    Label totalQuestions = new Label();
    int totalSelected = 0;


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
            errorLabel.setText("Must enter valid input (1-" + totalSelected + ") and click verify input");
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

    public void setUpCategories(){
        int row = 0;
        for(Category category : Category.values()){
            int questionCount = 0;
            if(QsInEachCategory.containsKey(category.name())){
                questionCount = QsInEachCategory.get(category.name());
            }
            RadioButton radioButton = new RadioButton(category.name() + "  ( " + questionCount + " )");
            radioButton.setId(category.name().toLowerCase());
            radioButton = clickedListener(radioButton, questionCount);
            categories.add(radioButton, 0, row);
            row++;
        }
    }

    public RadioButton clickedListener(RadioButton radioButton, int questionCount){
        radioButton.selectedProperty().addListener((observable, wasSelected, isSelected) -> {
            if (isSelected){
                totalSelected += questionCount;
                totalOfSelectedCategories.setText(totalSelected + " questions available");
            }
        });
        radioButton.selectedProperty().addListener((observable, wasSelected, isSelected) -> {
            if (wasSelected){
                totalSelected -= questionCount;
                totalOfSelectedCategories.setText(totalSelected + " questions available");
            }
        });
        return radioButton;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sb = new SceneBuilder();
        quizQuestions = new ArrayList<>();
        totalOfSelectedCategories.setText(totalSelected + " questions available");
        setUpCategories();
        totalQuestions.setText(String.valueOf(SqlCaller.getDBSize()) + " questions total");
    }

    @Override
    public void initData(Object parameter) {

    }
}
