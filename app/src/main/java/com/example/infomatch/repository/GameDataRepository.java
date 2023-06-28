package com.example.infomatch.repository;

import android.content.Context;

import com.example.infomatch.data.AppDataBase;
import com.example.infomatch.data.GameDataDao;
import com.example.infomatch.data.GameResult;

import java.util.List;

public class GameDataRepository {

    private AppDataBase db;
    public GameDataRepository(Context context) {
        db = AppDataBase.getDatabase(context);
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
        return db.userDao().getAll();
    }

    public void insert(GameResult gameResult) {
        db.userDao().insert(gameResult);
    }

    public void delete(GameResult gameResult) {
        db.userDao().delete(gameResult);
    }

}
