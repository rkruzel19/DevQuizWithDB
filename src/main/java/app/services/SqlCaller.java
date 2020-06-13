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
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return questionList;
    }

    public static Question getQuestionById(int questionId){
        String sql = "select * from question where id = " + questionId;
        Question questionToAdd = new Question();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                questionToAdd.setId(questionId);
                System.out.println(questionToAdd.getId());
                questionToAdd.setQuery(resultSet.getObject("question").toString());
                System.out.println(questionToAdd.getQuery());
                questionToAdd.setCorrectAnswer(resultSet.getObject("correctAnswer").toString());
                System.out.println(questionToAdd.getCorrectAnswer());
                questionToAdd.setAnswers(getQuestionChoices(questionId));
                System.out.println(questionToAdd.getAnswers());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return questionToAdd;
    }

    public static List<String> getQuestionChoices(int questionId){
        List<String> choices = new ArrayList<>();
        String sql = "select * from choices where id = " + questionId;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
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

    public static void addQuestionToDB(int id, String question, String correctAnswer, List<String> choices){

        try {
            String sql = "INSERT INTO quizdb.question (`id`, `question`, `correctAnswer`, `choicesId`) VALUES ('" + id + "', '" + question + "', '" + correctAnswer + "', '" + id + "')";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            int result = preparedStatement.executeUpdate();
            addQuestionChoicesToDB(id, choices);
//            sql = "INSERT INTO quizdb.choices (`id`, `choice1`, `choice2`, `choice3`, `choice4`) VALUES ('" + id + "', '" + choices.get(0) + "', '" + choices.get(1) + "', '" + choices.get(2) + "', '" + choices.get(3) + "')";
//            preparedStatement = con.prepareStatement(sql);
//            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void addQuestionChoicesToDB(int id, List<String> choices){
        try {
            String sql = "INSERT INTO quizdb.choices (`id`, `choice1`, `choice2`, `choice3`, `choice4`) VALUES ('" + id + "', '" + choices.get(0) + "', '" + choices.get(1) + "', '" + choices.get(2) + "', '" + choices.get(3) + "')";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            int result = preparedStatement.executeUpdate();
        } catch (Exception exception){
            exception.printStackTrace();
        }

    }

    public static int getDBSize(){
        String sql = "select count(*) from quizdb.question";
        int dbSize = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dbSize = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dbSize;
    }

}
