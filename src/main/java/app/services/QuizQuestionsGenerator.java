package app.services;

import app.dao.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizQuestionsGenerator {

    public static List<Question> generateEntireQuestionList(){
        return randomizeQuestions(SqlCaller.getAllQuestions());
    }

    public static List<Question> generateAllSelectedList(List<String> categories){
        return randomizeQuestions(SqlCaller.getAllSelectedQuestions(categories));
    }

    public static List<Question> generateXQuestionsFromSelected(List<String> categories, int numberOfQs){
        return randomizeQuestions(generateAllSelectedList(categories)).subList(0, numberOfQs);
    }
    public static List<Question> randomizeQuestions(List<Question> questions){
        List<Question> randomizedQuestions = new ArrayList<>();
        List<Integer> questionIndexList = new ArrayList<>();
        for(int i = 0; i < questions.size(); i++){
            int questionIndex = getRandomQuestionIndex(questions.size());
            while (questionIndexList.contains(questionIndex)){
                questionIndex = getRandomQuestionIndex((questions.size()));
            }
            questionIndexList.add(questionIndex);
            randomizedQuestions.add(questions.get(questionIndex));
        }
        return randomizedQuestions;
    }

    public static int getRandomQuestionIndex(int maxIndex){
        return new Random().nextInt(maxIndex);
    }


}

