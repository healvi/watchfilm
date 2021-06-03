package com.healvimaginer.watchfilm.data.source.local.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
@Dao
interface FilmDao {
    @Query("SELECT * FROM film ORDER BY contentId ASC")
    fun getAllFilm(): DataSource.Factory<Int, FilmsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(film: List<FilmsEntity>)

    @Update
    fun updateFilm(film: FilmsEntity)

    @Delete
    fun deleteFilm(film: FilmsEntity)

    @Query("SELECT * FROM film where contentId = :checkLogin")
    fun getFilm(checkLogin:String): LiveData<FilmsEntity>

}