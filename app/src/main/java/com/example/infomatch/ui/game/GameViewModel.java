package com.example.infomatch.ui.game;


import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.infomatch.gamePlay.Cards;


public class GameViewModel extends ViewModel {


    public Boolean timer = null;
    public int cardsAmount = 10;
    public String userName;

    public String qArray[] = new String[] {
            "q0", "q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9", "q10", "q11", "q12", "q13", "q14", "q15", "q16","q17" };

    public String aArray[] = new String[] {
            "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10", "a11", "a12", "a13", "a14", "a15", "a16","a17" };

    public Cards cardGame;

    public String cardsPositionsArray[] = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11"};

    public void setCardGame() {
        this.cardGame = new Cards(cardsAmount,qArray,aArray);
    }
}


