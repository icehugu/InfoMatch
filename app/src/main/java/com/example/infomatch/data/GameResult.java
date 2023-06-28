package com.example.infomatch.data;

public class GameResult {
    private String name;
    private int score;

    private int numOfPairs;
    private double time;

    public GameResult(String name, int score, double time,int numOfPairs) {
        setName(name);
        setScore(score);
        setTime(time);
        setNumOfPairs(numOfPairs);
    }


    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public double getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setNumOfPairs(int numOfPairs) {
        this.numOfPairs = numOfPairs;
    }

    public int getNumOfPairs() {
        return numOfPairs;
    }
}
