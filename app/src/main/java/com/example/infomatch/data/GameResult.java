package com.example.infomatch.data;

public class GameResult {
    private String name;
    private int score;
    private double time;

    public GameResult(String name, int score, double time) {
        setName(name);
        setScore(score);
        setTime(time);
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
}
