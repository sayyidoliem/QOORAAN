package com.olimhousestudio.qooraan.di

import android.content.Context
import com.olimhousestudio.qooraan.data.datasource.local.preference.AppPreferencesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.olimhousestudio.qooraan.data.datasource.local.preference.LastReadPreferencesDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideAppPreferencesDataSource(
        @ApplicationContext context: Context
    ): AppPreferencesDataSource {
        return AppPreferencesDataSource(context)
    }

    @Provides
    @Singleton
    fun provideLastReadPreferencesDataSource(
        @ApplicationContext context: Context
    ): LastReadPreferencesDataSource {
        return LastReadPreferencesDataSource(context)
    }
}