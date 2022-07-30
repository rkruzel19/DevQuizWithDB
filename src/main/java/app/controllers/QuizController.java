package app.controllers;

import app.dao.Question;
import app.services.SceneBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class QuizController implements Initializable, Controller {

    @FXML
    Button submitAnswerButton;
    @FXML
    Label questionNumber;
    @FXML
    Label questionCategory;
    @FXML
    Label questionText;
    @FXML
    RadioButton option1;
    @FXML
    RadioButton option2;
    @FXML
    RadioButton option3;
    @FXML
    RadioButton option4;
    @FXML
    Button homeButton;
    @FXML
    Label score;
    @FXML
    Label numberOfQuestions;
    @FXML
    ToggleGroup answerList;
    @FXML
    Label errorLabel;

    Question currentQuestion;
    int correctAnswers;
    int questionsAnswered;
    int currentQuestionNumber;
    List<Question> quizList;
    SceneBuilder sb;

    public void backToWelcome() throws Exception{
        sb.setNewScene((Stage)homeButton.getScene().getWindow(), "welcome");
    }

    public void submitAnswerButton() throws Exception{
        try {
            updateScore();
            if (questionsAnswered == quizList.size()) {
                // games over
                sb.setNewSceneWithParameters((Stage) submitAnswerButton.getScene().getWindow(), "endOfQuiz", score);

            } else {
                // continue to next question
                currentQuestionNumber++;
                currentQuestion = quizList.get(currentQuestionNumber);
                setQuestionNumber(currentQuestionNumber);
                setQuestionCategory(currentQuestion.getCategory().name());
                setQuestionText(currentQuestion.getQuestionString());
                setChoices(currentQuestion.getAnswers());
                errorLabel.setText("");
            }
        } catch (Exception e){
            errorLabel.setText("You must choose an option");
        }
    }

    public void setQuestionNumber(int questionNumber){
        this.questionNumber.setText("Question #" + (questionNumber + 1));
    }

    public Question getQuestionFromList(int nextQuestion){
        return quizList.get(nextQuestion);
    }

    public void setQuestionText(String questionText){
        this.questionText.setText(questionText);
    }

    public void setQuestionCategory(String category){
        this.questionCategory.setText((category));
    }

    public void setChoices(List<String> choices){
        option1.setText(choices.get(0));
        option2.setText(choices.get(1));
        option3.setText(choices.get(2));
        option4.setText(choices.get(3));
    }

    public boolean checkAnswer(String correctAnswer, String userGuess){
        return correctAnswer.equals(userGuess);
    }

    public void updateScore(){
        RadioButton selectedRadioButton = (RadioButton) answerList.getSelectedToggle();
        boolean isCorrect = checkAnswer(currentQuestion.getCorrectAnswer(), selectedRadioButton.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Question Result");


        if(isCorrect){
            alert.setHeaderText("Correct!!");
            alert.setContentText("You got it right!");
            alert.showAndWait();
            correctAnswers++;
        } else {
            alert.setHeaderText("Wrong...");
            alert.setContentText("Try harder next time.\nThe correct answer is: \"" + currentQuestion.getCorrectAnswer() + "\"");
            alert.showAndWait();
        }
        questionsAnswered++;
        setScore();
        answerList.selectToggle(null);
    }

    public void printQuestions(){
        quizList.forEach(System.out::println);
    }

    public void setScore(){
        score.setText(correctAnswers + " correct out of " + questionsAnswered);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sb = new SceneBuilder();
        currentQuestionNumber = 0;
        questionsAnswered = 0;
        correctAnswers = 0;
        setScore();

    }

    @Override
    public void initData(Object parameter) {
        this.quizList = (List<Question>)parameter;

        // Set first question
        currentQuestion = getQuestionFromList(currentQuestionNumber);
        setQuestionNumber(currentQuestionNumber);
        setQuestionCategory(currentQuestion.getCategory().name());
        setQuestionText(currentQuestion.getQuestionString());
        setChoices(currentQuestion.getAnswers());
        this.numberOfQuestions.setText(quizList.size() + " questions");
    }
}
