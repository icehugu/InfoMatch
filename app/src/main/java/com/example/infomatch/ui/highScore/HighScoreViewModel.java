package com.example.infomatch.ui.highScore;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.infomatch.data.GameResult;
import com.example.infomatch.repository.GameDataRepository;

import java.util.List;

public class HighScoreViewModel extends AndroidViewModel {


    public String userName = "";

    public GameDataRepository repository;

    private final LiveData<List<GameResult>> mAllResults;

    private LiveData<List<GameResult>> mAllResultsByUsername;



    public HighScoreViewModel(Application application) {
        super(application);
        repository = new GameDataRepository(application.getApplicationContext());
        mAllResults = repository.getAll();
        mAllResultsByUsername = repository.getAllResultsByUsername(userName);
    }

    public void getAllResultsByUsername(String username) {
        //mAllResultsByUsername = repository.getAll();
    }

    public LiveData<List<GameResult>> getAll() {
        return mAllResults;
    }

    public void deleteItem(GameResult gameResult) {
        repository.delete(gameResult);
    }


}
