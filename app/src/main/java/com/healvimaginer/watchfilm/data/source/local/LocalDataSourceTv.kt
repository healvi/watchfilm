package com.healvimaginer.watchfilm.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.FavoriteTvDao
import com.healvimaginer.watchfilm.data.source.local.room.dao.TvDao

class LocalDataSourceTv(private val tvDao: TvDao, private val favoriteTvDao: FavoriteTvDao) {
    companion object {
        private var INSTANCE: LocalDataSourceTv? = null

        fun getInstance(tvDao: TvDao,favoriteTvDao: FavoriteTvDao): LocalDataSourceTv =
            INSTANCE ?: LocalDataSourceTv(tvDao,favoriteTvDao)
    }

    fun getAllTv(): DataSource.Factory<Int, TvEntity> = tvDao.getAllTv()
    fun getTv(checkLogin:String):LiveData<TvEntity> = tvDao.getTv(checkLogin)
    fun insertTv(tv:List<TvEntity>) = tvDao.insertTv(tv)
    fun updateFilm(tv: TvEntity) = tvDao.updateTv(tv)

    fun getAllTvFavoritePagging(): DataSource.Factory<Int, FavoriteTvEntity> = favoriteTvDao.getAllTvPagging()
    fun findTvFavorite(checkLogin:String):LiveData<FavoriteTvEntity> = favoriteTvDao.findTv(checkLogin)
    fun insertTvFavorite(favorite: FavoriteTvEntity) = favoriteTvDao.insertTv(favorite)
    fun deleteTvFavorite(favorite: FavoriteTvEntity) = favoriteTvDao.deleteTv(favorite)
}