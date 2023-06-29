package com.example.infomatch.data;


import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gameresult")
public class GameResult {

    @PrimaryKey(autoGenerate = true)
    private int uid = 0;
    @ColumnInfo(name = "username")
    private String name;
    @ColumnInfo(name = "score")
    private int score;
    @ColumnInfo(name = "pairs")
    private int numOfPairs;
    @ColumnInfo(name = "time")
    private double time;
    @ColumnInfo(name = "dateAndTime")
    private String dateAndTime;

    public GameResult(String name, int score, double time,int numOfPairs, String dateAndTime) {
        setName(name);
        setScore(score);
        setTime(time);
        setNumOfPairs(numOfPairs);
        setDateAndTime(dateAndTime);
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getUid() {
        return uid;
    }

    public String getDateAndTime() {
        return dateAndTime;
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

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
