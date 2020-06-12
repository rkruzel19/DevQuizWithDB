package app.services;

import app.dao.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlCaller {

    public static Connection con = ConnectionUtil.conDB();

    public static List<Question> getAllQuestions(){
        String sql = "select * from question";
        List<Question> questionList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int questionId = Integer.parseInt(resultSet.getObject("id").toString());
                String question = resultSet.getObject("question").toString();
                String correctAnswer = resultSet.getObject("correctAnswer").toString();
                List<String> choices = getQuestionChoices(questionId);
                Question questionToAdd = new Question(questionId, question, choices, correctAnswer);
                questionList.add(questionToAdd);
                continue;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return questionList;
    }

    public static List<String> getQuestionChoices(int questionId){
        List<String> choices = new ArrayList<>();
        String sql = "select * from choices where id = " + questionId;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                choices.add(resultSet.getString("choice1"));
                choices.add(resultSet.getString("choice2"));
                choices.add(resultSet.getString("choice3"));
                choices.add(resultSet.getString("choice4"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return choices;
    }

}
