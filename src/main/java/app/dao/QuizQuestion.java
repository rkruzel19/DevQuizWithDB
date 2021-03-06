package app.dao;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private int id;
    private String query;
    private String correctAnswer;
    private List<String> answers;

    public Question() {
        id = 0;
        query = "";
        answers = new ArrayList<>();
        correctAnswer = "";
    }

    public Question(int id, String query, List<String> answers, String correctAnswer) {
        this.id = id;
        this.query = query;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", query='" + query + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", answers=" + answers +
                '}';
    }
}

