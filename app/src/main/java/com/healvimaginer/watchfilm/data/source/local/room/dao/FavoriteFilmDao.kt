package com.healvimaginer.watchfilm.data.source.local.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity

@Dao
interface FavoriteFilmDao {

    @Query("SELECT * from FavoriteFilm ORDER BY contentId ASC")
    fun getAllFilmPagging(): LiveData<List<FavoriteFilmEntity>>

    @Query("SELECT * FROM FavoriteFilm where contentId = :checkLogin")
    fun findFilm(checkLogin:String): LiveData<FavoriteFilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(favorite: FavoriteFilmEntity)

    @Delete
    fun deleteFilm(favorite: FavoriteFilmEntity)

    @Query("SELECT * FROM FavoriteFilm")
    fun checkfilm(): LiveData<List<FavoriteFilmEntity>>
}

