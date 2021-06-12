package com.healvimaginer.watchfilm.data.source.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.FavoriteFilmDao

@Database(entities = [FavoriteFilmEntity::class], version = 1, exportSchema = false)
abstract class FavoriteFilmDatabase : RoomDatabase() {
    abstract fun favoriteFilmDao(): FavoriteFilmDao
//    companion object {
//        @Volatile
//        private var INSTANCE: FavoriteFilmDatabase? = null
//
//        fun getInstance(context: Context): FavoriteFilmDatabase =
//            INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    FavoriteFilmDatabase::class.java,
//                    "FavoriteFilm.db"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//    }
}