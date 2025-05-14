package com.olimhousestudio.qooraan.data.datasource.local

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.DatabaseView
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.RoomDatabase
import com.olimhousestudio.qooraan.R
import com.olimhousestudio.qooraan.data.datasource.local.entities.Qoran
import com.olimhousestudio.qooraan.data.model.AyatSearch
import com.olimhousestudio.qooraan.data.model.Juz
import com.olimhousestudio.qooraan.data.model.Page
import com.olimhousestudio.qooraan.data.model.Surah
import com.olimhousestudio.qooraan.data.model.SurahJuz
import com.olimhousestudio.qooraan.data.model.SurahSearch


@Database(
    entities = [Qoran::class],
    views = [Surah::class, Juz::class, Page::class, SurahJuz::class, SurahSearch::class, AyatSearch::class],
    version = 1
)
abstract class QoranDatabase : RoomDatabase() {
    abstract fun dao(): QoranDao

    companion object {
        @Volatile
        private var INSTANCE: QoranDatabase? = null

        fun getInstance(context: Context): QoranDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(context: Context): QoranDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                QoranDatabase::class.java,
                "qoran.db"
            ).createFromInputStream {
                context.resources.openRawResource(R.raw.qoran)
            }.fallbackToDestructiveMigration(false).build()
        }
    }
}












