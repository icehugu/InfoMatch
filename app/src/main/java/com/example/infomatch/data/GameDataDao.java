package com.example.infomatch.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameDataDao {

    @Query("SELECT * FROM gameresult")
    List<GameResult> getAll();

    @Insert
    void insert(GameResult gameresult);

    @Delete
    void delete(GameResult gameresult);
}
