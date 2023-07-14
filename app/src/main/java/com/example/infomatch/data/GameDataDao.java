package com.example.infomatch.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface GameDataDao {

    @Query("SELECT * FROM gameresult")
    LiveData<List<GameResult>> getAll();

    @Query("SELECT * FROM gameresult WHERE username = :username")
    LiveData<List<GameResult>> getAllByUsername(String username);

    @Insert
    void insert(GameResult gameresult);

    @Delete
    void delete(GameResult gameresult);
}
