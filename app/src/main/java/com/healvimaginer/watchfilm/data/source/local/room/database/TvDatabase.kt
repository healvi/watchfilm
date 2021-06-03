package com.healvimaginer.watchfilm.data.source.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.TvDao

@Database(entities = [TvEntity::class], version = 1, exportSchema = false)
abstract class TvDatabase : RoomDatabase() {
    abstract fun TvDao(): TvDao
    companion object {
        @Volatile
        private var INSTANCE: TvDatabase? = null

        fun getInstance(context: Context): TvDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TvDatabase::class.java,
                    "Tv.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}