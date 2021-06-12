package com.healvimaginer.watchfilm.core.data.source.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.FavoriteTvDao

@Database(entities = [FavoriteTvEntity::class], version = 1, exportSchema = false)
abstract class FavoriteTvDatabase : RoomDatabase() {
    abstract fun favoriteTvDao(): FavoriteTvDao
    companion object {
        @Volatile
        private var INSTANCE: FavoriteTvDatabase? = null

        fun getInstance(context: Context): FavoriteTvDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteTvDatabase::class.java,
                    "FavoriteTv.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}