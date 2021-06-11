package com.healvimaginer.watchfilm.data.source.local.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {
    @Query("SELECT * FROM tva ORDER BY contentId ASC")
    fun getAllTv(): Flow<List<TvEntity>>

    @Query("SELECT * FROM tva where contentId = :checkLogin")
    fun getTv(checkLogin:String): Flow<TvEntity>

    @Delete
    fun deleteTv(tv: TvEntity)

    @Update
    fun updateTv(tv: TvEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTv(tv: List<TvEntity>)
}