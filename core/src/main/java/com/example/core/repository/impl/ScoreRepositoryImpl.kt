package com.example.core.repository.impl

import com.example.core.db.Score
import com.example.core.db.ScoreDao
import com.example.core.repository.ScoreRepository
import javax.inject.Inject

class ScoreRepositoryImpl @Inject constructor(private val scoreDao: ScoreDao) : ScoreRepository {
    override suspend fun getAllScores(): List<Score> {
        return scoreDao.getAllScores()
    }

    override suspend fun saveScore(score: Score) {
        scoreDao.saveScore(score)
    }

    override suspend fun deleteScores() {
        scoreDao.deleteAll()
    }
}