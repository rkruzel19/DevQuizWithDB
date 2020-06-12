package app.services;

import app.dao.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizQuestionsGenerator {

    public static List<Question> generateEntireQuestionList(){
        return SqlCaller.getAllQuestions();
    }

    public static List<Question> generateXQuestions(int numberOfQs){
        List<Question> questionList = new ArrayList<>();
        List<Integer> questionIds = new ArrayList<>();
        for(int i = 1; i <= numberOfQs; i++){
            int questionId = getRandomQuestionId();
            while (duplicateQuestion(questionIds, questionId)){
                questionId = getRandomQuestionId();
            }
            questionIds.add(questionId);
            questionList.add(SqlCaller.getQuestionById(questionId));
        }
        return questionList;
    }

    public static int getRandomQuestionId(){
        return new Random().nextInt(SqlCaller.getDBSize()) + 1;
    }

    public static boolean duplicateQuestion(List<Integer> questionIds, Integer questionId){
        return questionIds.contains(questionId);
    }

}

