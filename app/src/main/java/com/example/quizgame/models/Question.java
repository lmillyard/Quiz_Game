package com.example.quizgame.models;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String text;
    private int points;
    private Answer answer1, answer2, answer3;

    public Question(String text, int points, Answer answer1, Answer answer2, Answer answer3) {
        this.text = text;
        this.points = points;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
    }

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }

    public Answer getAnswer1() {
        return answer1;
    }

    public Answer getAnswer2() {
        return answer2;
    }

    public Answer getAnswer3() {
        return answer3;
    }

    public static List<Question> getAllQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question("How tall is the Eiffel Tower?", 5,
                new Answer("324m", true),
                new Answer("552m", false),
                new Answer("154m", false)));
        list.add(new Question("How many moons does Jupiter have?", 10,
                new Answer("15", false),
                new Answer("79", true),
                new Answer("34", false)));
        list.add(new Question("What is the largest country by population?", 6,
                new Answer("China", true),
                new Answer("USA", false),
                new Answer("India", false)));
        list.add(new Question("Which former British colony was given back to China in 1997??", 8,
                new Answer("Shanghai", false),
                new Answer("Beijing", false),
                new Answer("Hong Kong", true)));
        list.add(new Question("What was Meta Platforms Inc formerly known as?", 2,
                new Answer("Instagram", false),
                new Answer("Facebook", true),
                new Answer("TikTok", false)));
        return list;
    }
}
