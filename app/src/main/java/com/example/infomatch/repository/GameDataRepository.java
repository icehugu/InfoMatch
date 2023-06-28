package com.example.infomatch.repository;

import android.content.Context;

import com.example.infomatch.data.AppDataBase;
import com.example.infomatch.data.GameDataDao;
import com.example.infomatch.data.GameResult;

import java.util.List;

public class GameDataRepository {

    private GameDataDao gameDataDao;

    public GameDataRepository(Context context) {

        AppDataBase db = AppDataBase.getDatabase(context);
        gameDataDao = db.gameDataDao();

    }
//    fun getExercises(workoutId: String) = exerciseDao?.getExercises(workoutId)
//
//    suspend fun addExercise(exercise_item: Exercise_Item) {
//        exerciseDao?.addExercise(exercise_item)
//    }
//
//    suspend fun deleteExercise(exercise_item: Exercise_Item) {
//        exerciseDao?.deleteExercise(exercise_item)
//    }
//
//    fun getItem(id: Int) = exerciseDao?.getExercise(id)

    public List<GameResult> getAll() {
        return gameDataDao.getAll();
    }

    public void insert(GameResult gameResult) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            gameDataDao.insert(gameResult);
        });
    }

    public void delete(GameResult gameResult) {
        gameDataDao.delete(gameResult);
    }

}
