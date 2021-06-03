package com.healvimaginer.watchfilm.data.source.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.FilmDao

@Database(entities = [FilmsEntity::class], version = 1, exportSchema = false)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun FilmDao(): FilmDao
    companion object {
        @Volatile
        private var INSTANCE: FilmDatabase? = null

        fun getInstance(context: Context): FilmDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FilmDatabase::class.java,
                    "Film.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}