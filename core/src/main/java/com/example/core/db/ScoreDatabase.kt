package com.example.core.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Score::class],
    version = 1,
    exportSchema = false
)
abstract class ScoreDatabase : RoomDatabase() {
    abstract fun scoreDao() : ScoreDao
}