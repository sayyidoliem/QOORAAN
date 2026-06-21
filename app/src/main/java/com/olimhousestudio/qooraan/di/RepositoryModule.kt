package com.olimhousestudio.qooraan.di

import com.olimhousestudio.qooraan.data.repositories.BookmarkRepositoryImpl
import com.olimhousestudio.qooraan.data.repositories.QuranRepositoryImpl
import com.olimhousestudio.qooraan.data.repositories.SettingsRepositoryImpl
import com.olimhousestudio.qooraan.domain.repositories.SettingsRepository
import com.olimhousestudio.qooraan.domain.repository.BookmarkRepository
import com.olimhousestudio.qooraan.domain.repository.QuranRepository
import com.olimhousestudio.qooraan.data.repositories.LastReadRepositoryImpl
import com.olimhousestudio.qooraan.domain.repository.LastReadRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindQuranRepository(
        impl: QuranRepositoryImpl
    ): QuranRepository

    @Binds
    @Singleton
    abstract fun bindBookmarkRepository(
        impl: BookmarkRepositoryImpl
    ): BookmarkRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository

    @Binds
    @Singleton
    abstract fun bindLastReadRepository(
        impl: LastReadRepositoryImpl
    ): LastReadRepository
}