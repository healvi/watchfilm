package com.healvimaginer.watchfilm.data.source.local.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
@Dao
interface TvDao {
    @Query("SELECT * FROM tv ORDER BY contentId ASC")
    fun getAllTv(): LiveData<List<TvEntity>>

    @Query("SELECT * FROM tv where contentId = :checkLogin")
    fun getTv(checkLogin:String):LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: List<TvEntity>)

    @Delete
    fun deleteTv(tv: TvEntity)

    @Update
    fun updateTv(tv: TvEntity)
}