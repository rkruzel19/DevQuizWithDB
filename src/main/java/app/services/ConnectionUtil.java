package app.services;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

    public static Connection conDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb?useSSL=false", "root", "password");
            System.out.println("Connected");
            return con;
        } catch (Exception ex) {
            System.out.println("no connection");
            return null;
        }
    }

}
