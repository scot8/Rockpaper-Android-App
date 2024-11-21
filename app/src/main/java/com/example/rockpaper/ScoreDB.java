package com.example.rockpaper;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Score.class}, version = 3, exportSchema = false)
public abstract class ScoreDB extends RoomDatabase
{
    public static final String DB_NAME = "score_db";
    private static ScoreDB INSTANCE = null;
    public static ScoreDB getInstance(Context context)
    {   if (INSTANCE == null)
    {   INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ScoreDB.class,
                    DB_NAME
            ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build();
    }
        return INSTANCE;
    }
    public abstract ScoreDao scoreDao();
}