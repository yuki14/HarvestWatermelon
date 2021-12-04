package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.db.ScoreDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideScoreDb(@ApplicationContext context: Context): ScoreDatabase {
        return Room.databaseBuilder(context, ScoreDatabase::class.java,
            "score_db").build()
    }

    @Singleton
    @Provides
    fun provideScoreDao(database: ScoreDatabase) = database.scoreDao()
}