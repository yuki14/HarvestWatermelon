package com.example.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {
    @Query("select * from scores order by score desc")
    suspend fun getAllScores(): List<Score>

    @Insert
    suspend fun saveScore(score: Score)

    @Query("delete from scores")
    suspend fun deleteAll()
}