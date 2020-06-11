package app.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionPool {

    private static List<Question> questions = new ArrayList<>(Arrays.asList(
            new Question("Amy's Birthday", Arrays.asList("02/21", "12/04", "09/01", "08/08"), "09/01"),
            new Question("Amy's first pet?", Arrays.asList("Bruno", "Rocky", "Suki", "Goldy"), "Bruno"),
            new Question("Amy's favorite color?", Arrays.asList("Pink", "Yellow", "Red", "Blue"), "Blue"),
            new Question("Where am I from?", Arrays.asList("Philly", "Florida", "Englewood", "Delaware"), "Philly"),
            new Question("My favorite sport?", Arrays.asList("Soccer", "Hockey", "Football", "Baseball"), "Hockey"),
            new Question("Brennan's favorite youtube channel?", Arrays.asList("NetNinja", "ShareTheLove", "TheNewBoston", "SlimeBabies"), "ShareTheLove"),
            new Question("Mya's favorite animal?", Arrays.asList("Bunny", "Dog", "Cat", "Unicorn"), "Unicorn"),
            new Question("Mya's favorite food?", Arrays.asList("Spaghetti", "Chips", "Candy", "Ice cream"), "Candy"),
            new Question("Where are we moving?", Arrays.asList("Colorado", "Las Vegas", "California", "Delaware"), "Colorado"),
            new Question("32 x 6 = ?", Arrays.asList("190", "192", "202", "195"), "192"),

            new Question("Who is Philly's hockey team?", Arrays.asList("BlackHawks", "MooseKnuckles", "Flyers", "Bruins"), "Flyers"),
            new Question("Where were the 1980 Summer Olympics hosted?", Arrays.asList("Seoul, South Korea", "Tokyo, Japan", "Lake Placid, New York, US", "Moscow, Russia"), "Moscow, Russia"),
            new Question("Which is not a primitive type in java?", Arrays.asList("integer", "char", "boolean", "double"), "integer"),
            new Question("Who was the greatest team to ever play at The Dek?", Arrays.asList("TimberWolves", "WolfPack", "Assassins", "Marty Mcgee's"), "Assassins"),
            new Question("What is hockey played in sneakers called?", Arrays.asList("street", "dek", "roller", "ice"), "dek"),
            new Question("In football, how many points is a touchdown worth?", Arrays.asList("7", "8", "3", "6"), "6"),
            new Question("In hockey, what is the penalty for elbowing and drawing blood?", Arrays.asList("2 minutes", "5 minutes", "4 minutes", "10 minute game misconduct"), "4 minutes"),
            new Question("In soccer, how many players are on the field at a time?", Arrays.asList("11", "22", "20", "21"), "22"),
            new Question("What is the legal age to smoke cigarettes in the US?", Arrays.asList("21", "18", "25", "16"), "21"),
            new Question("How many states is Colorado connected to?", Arrays.asList("4", "5", "6", "7"), "6")
    ));

    public static List<Question> getQuestions() {
        return questions;
    }

}
