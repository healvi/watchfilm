package com.healvimaginer.watchfilm.data.source.local.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteFilmDao {

    @Query("SELECT * from FavoriteFilm ORDER BY contentId ASC")
    fun getAllFilmPagging(): Flow<List<FavoriteFilmEntity>>

    @Query("SELECT * FROM FavoriteFilm where contentId = :checkLogin")
    fun findFilm(checkLogin:String): Flow<FavoriteFilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(favorite: FavoriteFilmEntity)

    @Delete
    fun deleteFilm(favorite: FavoriteFilmEntity)

    @Query("SELECT * FROM FavoriteFilm")
    fun checkfilm(): Flow<List<FavoriteFilmEntity>>
}

