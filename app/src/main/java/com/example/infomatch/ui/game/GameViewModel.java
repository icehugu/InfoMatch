package com.example.infomatch.ui.game;


import android.app.Application;
import android.os.CountDownTimer;
import android.widget.Button;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.infomatch.data.GameResult;
import com.example.infomatch.gamePlay.Cards;
import com.example.infomatch.repository.GameDataRepository;

import java.util.Arrays;
import java.util.Collections;


public class GameViewModel extends AndroidViewModel {

    public GameDataRepository repository;
    public Boolean timer;
    public MutableLiveData<Boolean> gameEndLiveData;
    public int cardsAmount = 10;
    public String userName;
    public int pairsFound = 0;

    public int curCombo = 0;
    public int curScore = 0;
    private MutableLiveData<Long> timerLiveData;

    public GameViewModel(Application application) {
        super(application);
        this.timerLiveData = new MutableLiveData<>();
        this.gameEndLiveData = new MutableLiveData<>();
        //this.gameEndLiveData.setValue(false);
        //this.pairsFound = 0;
        //this.timer = null;
        repository = new GameDataRepository(application.getApplicationContext());
    }

    public void addItem(GameResult gameResult) {
        repository.insert(gameResult);
    }
    private long milliLeft;
    public String qArray[] = new String[] {
            "q0", "q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9", "q10", "q11", "q12", "q13", "q14", "q15", "q16","q17" };

    public String aArray[] = new String[] {
            "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10", "a11", "a12", "a13", "a14", "a15", "a16","a17" };

    public Cards cardGame;

    public Integer cardsPositionsArray[] = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11};

    Button button1 = null;

    Button button2 = null;

    public CountDownTimer countDownTimer;

    public Button[] gridButtons;

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

    public void startTimer(long timeLengthMilli) {

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(timeLengthMilli, 1000) {

            public void onTick(long millisUntilFinished) {
                timerLiveData.setValue(millisUntilFinished / 1000);
                milliLeft = millisUntilFinished;
            }

            public void onFinish() {
                timerEnd();
            }
        };

        countDownTimer.start();

    }

    public void endGame() {
        if(this.timer) {
            countDownTimer.cancel();
        }
        gameEndLiveData.setValue(true);
    }

    public void timerResume() {
        startTimer(milliLeft);
    }

    public void timerStop() {
        countDownTimer.cancel();
    }

    public void setUpGame(){
        this.gameEndLiveData.setValue(false);
        Collections.shuffle(Arrays.asList(this.cardsPositionsArray));
        this.button1 = null;
        this.button2 = null;
        this.pairsFound = 0;
        for (int i = 0; i < 6; i++) {
            this.gridButtons[this.cardsPositionsArray[i*2]].setText(this.cardGame.getQaPair().keySet().toArray()[i].toString());
            this.gridButtons[this.cardsPositionsArray[i*2]].setTextScaleX(0);
            this.gridButtons[this.cardsPositionsArray[i*2]].setClickable(true);
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setText(this.cardGame.getQaPair().get(this.cardGame.getQaPair().keySet().toArray()[i].toString()));
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setTextScaleX(0);
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setClickable(true);
        }
        if(this.timer) {
            this.startTimer(this.cardsAmount * 2500);
        }
        this.curScore = 0;
        this.scoreLiveData.setValue(this.curScore);
        this.curCombo=0;
    }

    public void nextCards(){
        Collections.shuffle(Arrays.asList(this.cardsPositionsArray));
        this.button1 = null;
        this.button2 = null;
        for (int i = 0; i < 6; i++) {
            this.gridButtons[this.cardsPositionsArray[i*2]].setText(this.cardGame.getQaPair().keySet().toArray()[i].toString());
            this.gridButtons[this.cardsPositionsArray[i*2]].setTextScaleX(0);
            this.gridButtons[this.cardsPositionsArray[i*2]].setClickable(true);
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setText(this.cardGame.getQaPair().get(this.cardGame.getQaPair().keySet().toArray()[i].toString()));
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setTextScaleX(0);
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setClickable(true);
        }
    }


    public MutableLiveData<Integer> getScoreLiveData() {
        return scoreLiveData;
    }

    public LiveData<Long> getTimerLiveData() {
        return timerLiveData;
    }

    public MutableLiveData<Boolean> getGameEndLiveData() {
        return gameEndLiveData;
    }
}


