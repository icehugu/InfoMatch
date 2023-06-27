package com.example.infomatch.ui.game;


import android.app.Application;
import android.os.CountDownTimer;
import android.widget.Button;

import androidx.lifecycle.ViewModel;

import com.example.infomatch.gamePlay.Cards;

import java.util.Arrays;
import java.util.Collections;


public class GameViewModel extends ViewModel {


    public Boolean timer = null;
    public int cardsAmount = 10;
    public String userName;

    public int pairsFound = 0;

    public String qArray[] = new String[] {
            "q0", "q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9", "q10", "q11", "q12", "q13", "q14", "q15", "q16","q17" };

    public String aArray[] = new String[] {
            "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10", "a11", "a12", "a13", "a14", "a15", "a16","a17" };

    public Cards cardGame;

    public Integer cardsPositionsArray[] = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11};

    Button button1 = null;

    Button button2 = null;

    public CountDownTimer countDownTimer;

    Button[] gridButtons = new Button[12];

    public void setCardGame() {
        this.cardGame = new Cards(cardsAmount,qArray,aArray);
    }

    public boolean checkPairOfCards(Button button1,Button button2){
        if(Arrays.asList(this.qArray).contains(button1.getText()) &&
                Arrays.asList(this.aArray).contains(button2.getText())) {
            if(Arrays.asList(this.qArray).indexOf(button1.getText()) == Arrays.asList(this.aArray).indexOf(button2.getText())) {
                return true;
            }
            else {
                return false;
            }
        }

        if(Arrays.asList(this.aArray).contains(button1.getText()) &&
                Arrays.asList(this.qArray).contains(button2.getText())) {
            if(Arrays.asList(this.aArray).indexOf(button1.getText()) == Arrays.asList(this.qArray).indexOf(button2.getText())) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
}


