package com.example.infomatch.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {GameResult.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {


    public abstract GameDataDao gameDataDao();


    private static volatile AppDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


//    fun getDatabase(context: Context) = instance ?: synchronized(this) {
//        Room.databaseBuilder(context.applicationContext, WorkoutItemDatabase::class.java, "db")
//                    .build()
//    }

    public static AppDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDataBase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}