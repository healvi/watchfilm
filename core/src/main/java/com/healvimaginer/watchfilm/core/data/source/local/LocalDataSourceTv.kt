package com.healvimaginer.watchfilm.core.data.source.local


import com.healvimaginer.watchfilm.core.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.core.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.core.data.source.local.room.dao.FavoriteTvDao
import com.healvimaginer.watchfilm.core.data.source.local.room.dao.TvDao
import kotlinx.coroutines.flow.Flow


class LocalDataSourceTv(private val tvDao: TvDao, private val favoriteTvDao: FavoriteTvDao) {

    fun getAllTv(): Flow<List<TvEntity>> = tvDao.getAllTv()
    fun getTv(checkLogin:String):Flow<TvEntity> = tvDao.getTv(checkLogin)
    suspend fun insertTv(tv:List<TvEntity>) = tvDao.insertTv(tv)
    fun updateFilm(tv: TvEntity) = tvDao.updateTv(tv)

    fun getAllTvFavoritePagging(): Flow<List<FavoriteTvEntity>> = favoriteTvDao.getAllTvPagging()
    fun findTvFavorite(checkLogin:String):Flow<FavoriteTvEntity> = favoriteTvDao.findTv(checkLogin)
    fun insertTvFavorite(favorite: FavoriteTvEntity) = favoriteTvDao.insertTv(favorite)
    fun deleteTvFavorite(favorite: FavoriteTvEntity) = favoriteTvDao.deleteTv(favorite)
}