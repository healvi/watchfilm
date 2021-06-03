package com.healvimaginer.watchfilm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.FavoriteFilmDao

@Database(entities = [FavoriteFilmEntity::class], version = 1, exportSchema = false)
abstract class FilmTestDatabase : RoomDatabase() {
    abstract fun favoriteFilmDao(): FavoriteFilmDao
    companion object {
        @Volatile
        private var INSTANCE: FilmTestDatabase? = null

        fun getInstance(context: Context): FilmTestDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.inMemoryDatabaseBuilder(
                    context.applicationContext,
                    FilmTestDatabase::class.java,
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }

    }
}