package com.healvimaginer.watchfilm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.FavoriteTvDao

@Database(entities = [FavoriteTvEntity::class], version = 1, exportSchema = false)
abstract class TvTestDatabase : RoomDatabase() {
    abstract fun favoriteTvDao(): FavoriteTvDao
    companion object {
        @Volatile
        private var INSTANCE: TvTestDatabase? = null

        fun getInstance(context: Context): TvTestDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.inMemoryDatabaseBuilder(
                    context.applicationContext,
                    TvTestDatabase::class.java,
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}