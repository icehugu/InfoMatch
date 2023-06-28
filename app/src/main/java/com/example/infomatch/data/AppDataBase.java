package com.example.infomatch.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;




@Database(entities = {GameResult.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract GameDataDao userDao();


    private static AppDataBase instance = null;

//    fun getDatabase(context: Context) = instance ?: synchronized(this) {
//        Room.databaseBuilder(context.applicationContext, WorkoutItemDatabase::class.java, "db")
//                    .build()
//    }

    public static AppDataBase getDatabase(Context context) {
        instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "db").build();
        return instance;
    }
}