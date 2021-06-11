package com.healvimaginer.watchfilm.data.source.local.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteTvDao {
    @Query("SELECT * from FavoriteTv ORDER BY contentId ASC")
    fun getAllTvPagging(): Flow<List<FavoriteTvEntity>>

    @Query("SELECT * FROM FavoriteTv where contentId = :checkLogin")
    fun findTv(checkLogin:String):Flow<FavoriteTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(favorite: FavoriteTvEntity)

    @Delete
    fun deleteTv(favorite: FavoriteTvEntity)
}
