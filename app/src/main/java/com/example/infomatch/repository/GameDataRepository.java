package com.example.infomatch.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.infomatch.data.AppDataBase;
import com.example.infomatch.data.GameDataDao;
import com.example.infomatch.data.GameResult;
import java.util.List;

public class GameDataRepository {

    private GameDataDao gameDataDao;
    private LiveData<List<GameResult>> mAllResults;



    public GameDataRepository(Context context) {

        AppDataBase db = AppDataBase.getDatabase(context);
        gameDataDao = db.gameDataDao();
        mAllResults = gameDataDao.getAll();


    }

    public LiveData<List<GameResult>> getAll() {
        return mAllResults;
    }

    public void insert(GameResult gameResult) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            gameDataDao.insert(gameResult);
        });
    }

    public void delete(GameResult gameResult) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            gameDataDao.delete(gameResult);
        });
    }

    public LiveData<List<GameResult>> getAllResultsByUsername(String username) {
        return gameDataDao.getAllByUsername(username);
    }

}
