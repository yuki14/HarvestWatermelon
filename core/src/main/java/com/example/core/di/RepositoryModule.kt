package com.example.core.di

import com.example.core.repository.ScoreRepository
import com.example.core.repository.impl.ScoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    @ViewModelScoped
    fun bindScoreRepositoryImpl(scoreRepositoryImpl: ScoreRepositoryImpl): ScoreRepository
}