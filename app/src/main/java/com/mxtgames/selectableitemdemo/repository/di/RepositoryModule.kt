package com.mxtgames.selectableitemdemo.repository.di

import com.mxtgames.selectableitemdemo.di.IOCoroutineDispatcher
import com.mxtgames.selectableitemdemo.repository.ProgramsRepository
import com.mxtgames.selectableitemdemo.repository.ProgramsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideItemsRepository(
        @IOCoroutineDispatcher isDispatcher: CoroutineDispatcher,
    ): ProgramsRepository {
        return ProgramsRepositoryImpl(isDispatcher)
    }
}