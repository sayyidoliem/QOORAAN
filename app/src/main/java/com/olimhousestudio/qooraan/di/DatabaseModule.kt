package com.olimhousestudio.qooraan.di

import android.content.Context
import androidx.room.Room
import com.olimhousestudio.qooraan.R
import com.olimhousestudio.qooraan.data.datasource.local.BookmarkDao
import com.olimhousestudio.qooraan.data.datasource.local.BookmarkDatabase
import com.olimhousestudio.qooraan.data.datasource.local.QoranDao
import com.olimhousestudio.qooraan.data.datasource.local.QoranDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideQoranDatabase(
        @ApplicationContext context: Context
    ): QoranDatabase {
        return Room.databaseBuilder(
            context,
            QoranDatabase::class.java,
            "qoran.db"
        )
            .createFromInputStream {
                context.resources.openRawResource(R.raw.qoran)
            }
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideQoranDao(database: QoranDatabase): QoranDao {
        return database.dao()
    }

    @Provides
    @Singleton
    fun provideBookmarkDatabase(
        @ApplicationContext context: Context
    ): BookmarkDatabase {
        return Room.databaseBuilder(
            context,
            BookmarkDatabase::class.java,
            "bookmark.db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideBookmarkDao(database: BookmarkDatabase): BookmarkDao {
        return database.bookmarkDao()
    }
}