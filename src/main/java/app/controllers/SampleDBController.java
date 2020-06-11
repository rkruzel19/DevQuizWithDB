package app.controllers;

import app.services.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SampleDBController {

    Connection con = ConnectionUtil.conDB();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void testDBConnection() {
        String sql = "select * from question";

        try {
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("End of questions");
            } else {
                int questionId = Integer.parseInt(resultSet.getObject("id").toString());
                String question = resultSet.getObject("question").toString();
                String correctAnswer = resultSet.getObject("correctAnswer").toString();
                System.out.println("Question# " + questionId + "\nQuestion: " + question + "\nCorrect Answer: " + correctAnswer);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
