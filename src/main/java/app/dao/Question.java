package app.dao;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private int id;
    private String questionString;
    private String correctAnswer;
    private List<String> answers;
    private Category category;

    public Question() {
        id = 0;
        questionString = "";
        answers = new ArrayList<>();
        correctAnswer = "";
        category = null;
    }

    public Question(int id, String questionString, List<String> answers, String correctAnswer, Category category) {
        this.id = id;
        this.questionString = questionString;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionString='" + questionString + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", answers=" + answers +
                ", category=" + category +
                '}';
    }
}

