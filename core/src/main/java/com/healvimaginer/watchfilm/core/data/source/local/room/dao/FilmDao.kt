package com.healvimaginer.watchfilm.core.data.source.local.room.dao

import androidx.room.*
import com.healvimaginer.watchfilm.core.data.source.local.entity.FilmsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM filma ORDER BY contentId ASC")
    fun getAllFilm(): Flow<List<FilmsEntity>>

    @Update
    fun updateFilm(film: FilmsEntity)

    @Delete
    fun deleteFilm(film: FilmsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: List<FilmsEntity>)

    @Query("SELECT * FROM filma where contentId = :checkLogin")
    fun getFilm(checkLogin:String): Flow<FilmsEntity>


}