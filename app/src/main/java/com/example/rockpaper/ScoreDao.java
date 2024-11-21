package com.example.rockpaper;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface ScoreDao {

    @Query("SELECT id, userScore from score")
    List<Score> findUserScore();

    @Query("SELECT id, computerScore from score")
    List<Score> findCompScore();

    @Query ("DELETE FROM score")
    void deleteAll();

    @Query("SELECT * FROM score")
    List<Score> findAllData();


    @Update
    void updateScore(Score score);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert (Score data);
}
