package com.healvimaginer.watchfilm.data.source.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.FavoriteTvDao
import com.healvimaginer.watchfilm.data.source.local.room.dao.TvDao

@Database(entities = [FavoriteTvEntity::class], version = 1, exportSchema = false)
abstract class FavoriteTvDatabase : RoomDatabase() {
    abstract fun favoriteTvDao(): FavoriteTvDao
    companion object {
        @Volatile
        private var INSTANCE: TvDatabase? = null

        fun getInstance(context: Context): TvDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TvDatabase::class.java,
                    "FavoriteTv.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}