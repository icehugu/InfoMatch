package com.example.infomatch.ui.highScore;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.infomatch.data.GameResult;
import com.example.infomatch.repository.GameDataRepository;

import java.util.List;

public class HighScoreViewModel extends AndroidViewModel {
    public String userName;

    public GameDataRepository repository;



    public HighScoreViewModel(Application application) {
        super(application);
        repository = new GameDataRepository(application.getApplicationContext());
    }

    public List<GameResult> getAll() {
        return repository.getAll();
    }


}
