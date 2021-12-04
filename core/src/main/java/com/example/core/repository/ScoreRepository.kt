package com.example.core.repository

import com.example.core.db.Score

interface ScoreRepository {
    suspend fun getAllScores(): List<Score>

    suspend fun saveScore(score: Score)

    suspend fun deleteScores()
}