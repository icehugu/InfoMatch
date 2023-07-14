package com.example.infomatch.ui.game;

import android.app.Application;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.widget.Button;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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
    private MutableLiveData<Integer> scoreLiveData;

    private MediaPlayer mediaPlayer;

    private long milliLeft;
    public String qArray[] = new String[] {
            "how much\nis 1 mb\nin kb?", //q0
            "how much\nis 1 gb\nin mb?", //q1
            "how much\nis 1 tb\nin gb?", //q2
            "what is\n2^5?",//q3
            "when was\nmicrosoft\nfounded?", //q4
            "min(1,5)\n=?", //q5
            "who is\nthe\ninventor\nof the\ntelephone?", //q6
            "who is\nthe\ninventor\nof the\nelectricity", //q7
            "who is\nthe\nfounded\nApple?", //q8
            "when was\nthe turing\nmachine first\ndescribed?", //q9
            "when was\nthe iphone\nfirst launch\ndate?", //q10
            "what are\nthe values\nin binary?", //q11
            "what are\nthe values\nof a\nboolean\nvariable?", //q12
            "what can\na int\nvariable\nnot hold?", //q13
            "x = 3\ny = 2\n(x*y)-y=?", //q14
            "what is\nHIT?", //q15
            "what are\nthe 3 main\ncolors?",//q16
            "what time\n it is?" };//q17

    public String aArray[] = new String[] {
            "1000\nkb", //a0
            "1000\nmb", //a1
            "1000\ngb", //a2
            "32", //a3
            "1975,\nApril 4", //a4
            "1", //a5
            "alexander\nbell", //a6
            "thomas\nedison", //a7
            "steve\njobs", //a8
            "1936", //a9
            "2007", //a10
            "0/1", //a11
            "true\nor\nfalse", //a12
            "1.5", //a13
            "4", //a14
            "Holon\nInstitute\nof\nTechnology", //a15
            "red\nYellow\nblue", //a16
            "hammer\ntime" }; //a17

    public Cards cardGame;

    public Integer cardsPositionsArray[] = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11};

    Button button1 = null;

    Button button2 = null;

    public CountDownTimer countDownTimer;

    public Button[] gridButtons;

    public GameViewModel(Application application) {
        super(application);
        this.timerLiveData = new MutableLiveData<>();
        this.gameEndLiveData = new MutableLiveData<>();
        this.scoreLiveData = new MutableLiveData<>();
        repository = new GameDataRepository(application.getApplicationContext());
    }

    public void addItem(GameResult gameResult) {
        repository.insert(gameResult);
    }

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
                endGame();
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
        this.cardGame.reShuffle();
        this.button1 = null;
        this.button2 = null;
        this.pairsFound = 0;
        for (int i = 0; i < 6; i++) {
            this.gridButtons[this.cardsPositionsArray[i*2]].setText(this.cardGame.getQaPair().keySet().toArray()[i].toString());
            this.gridButtons[this.cardsPositionsArray[i*2]].setTextScaleX(0);
            this.gridButtons[this.cardsPositionsArray[i*2]].setClickable(true);
            this.gridButtons[this.cardsPositionsArray[i*2]].setBackgroundColor(0xFF6200EE);
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setText(this.cardGame.getQaPair().get(this.cardGame.getQaPair().keySet().toArray()[i].toString()));
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setTextScaleX(0);
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setClickable(true);
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setBackgroundColor(0xFF6200EE);


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
            this.gridButtons[this.cardsPositionsArray[i*2]].setBackgroundColor(0xFF6200EE);
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setText(this.cardGame.getQaPair().get(this.cardGame.getQaPair().keySet().toArray()[i].toString()));
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setTextScaleX(0);
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setClickable(true);
            this.gridButtons[this.cardsPositionsArray[(i*2)+1]].setBackgroundColor(0xFF6200EE);
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

    public void updateCurScore() {
        this.scoreLiveData.setValue(this.curScore);
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}


