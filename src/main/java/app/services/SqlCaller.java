package app.services;

import app.dao.Category;
import app.dao.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
                Category category = Category.valueOf(resultSet.getObject("category").toString());
                Question questionToAdd = new Question(questionId, question, choices, correctAnswer, category);
                questionList.add(questionToAdd);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return questionList;
    }

    public static List<Question> getAllSelectedQuestions(List<String> categories){
        StringBuilder sql = new StringBuilder("select * from question where");
        List<Question> questionList = new ArrayList<>();
        for(String category : categories){
            if(categories.indexOf(category) == categories.size() - 1){
                sql.append(" category=\"").append(category).append("\"");
            } else {
                sql.append(" category=\"").append(category).append("\" or");
            }
        }

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int questionId = Integer.parseInt(resultSet.getObject("id").toString());
                String question = resultSet.getObject("question").toString();
                String correctAnswer = resultSet.getObject("correctAnswer").toString();
                List<String> choices = getQuestionChoices(questionId);
                Category category = Category.valueOf(resultSet.getObject("category").toString());
                Question questionToAdd = new Question(questionId, question, choices, correctAnswer, category);
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
                questionToAdd.setQuestionString(resultSet.getObject("question").toString());
                questionToAdd.setCorrectAnswer(resultSet.getObject("correctAnswer").toString());
                questionToAdd.setAnswers(getQuestionChoices(questionId));
                questionToAdd.setCategory(Category.valueOf(resultSet.getObject("category").toString()));
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

    public static void addQuestionToDB(int id, String question, String correctAnswer, List<String> choices, Category category){

        try {
            String sql = "INSERT INTO quizdb.question (`id`, `question`, `correctAnswer`, `choicesId`, `category`) VALUES ('" + id + "', '" + question + "', '" + correctAnswer + "', '" + id + "', '" + category.toString() + "')";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            int result = preparedStatement.executeUpdate();
//            con.close();
            addQuestionChoicesToDB(id, choices);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void addQuestionChoicesToDB(int id, List<String> choices){
        try {
            String sql = "INSERT INTO quizdb.choices (`id`, `choice1`, `choice2`, `choice3`, `choice4`) VALUES ('" + id + "', '" + choices.get(0) + "', '" + choices.get(1) + "', '" + choices.get(2) + "', '" + choices.get(3) + "')";
//            con = ConnectionUtil.conDB();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            int result = preparedStatement.executeUpdate();
//            con.close();
        } catch (Exception exception){
            exception.printStackTrace();
        }

    }

    public static HashMap<String, Integer> getEachCategorySize(){
        String sql = "SELECT category, COUNT(*) FROM quizdb.question GROUP BY category";
        HashMap<String, Integer> categories = new HashMap<>();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categories.put(resultSet.getString(1), resultSet.getInt(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
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
