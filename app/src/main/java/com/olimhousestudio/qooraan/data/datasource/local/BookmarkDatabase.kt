package com.olimhousestudio.qooraan.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.olimhousestudio.qooraan.data.datasource.local.entities.SurahBookmark
import com.olimhousestudio.qooraan.data.datasource.local.entities.Bookmark

@Database(
    entities = [Bookmark::class, SurahBookmark::class],
    version = 2
)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        @Volatile
        private var INSTANCE: BookmarkDatabase? = null

        fun getInstance(context: Context): BookmarkDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(context: Context): BookmarkDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                BookmarkDatabase::class.java,
                "bookmark.db"
            ).fallbackToDestructiveMigration(false).build()
        }
    }
}