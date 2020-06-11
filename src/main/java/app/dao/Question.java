package app.dao;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String query;
    private String correctAnswer;
    private List<String> answers;

    public Question() {
        query = "";
        answers = new ArrayList<>();
        correctAnswer = "";
    }

    public Question(String query, List<String> answers, String correctAnswer) {
        this.query = query;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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

    @Override
    public String toString() {
        return "Question{" +
                "query='" + query + '\'' +
                ", answers=" + answers +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}

