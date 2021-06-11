package com.healvimaginer.watchfilm.data.source.local


import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.FavoriteTvDao
import com.healvimaginer.watchfilm.data.source.local.room.dao.TvDao
import kotlinx.coroutines.flow.Flow


class LocalDataSourceTv(private val tvDao: TvDao, private val favoriteTvDao: FavoriteTvDao) {
    companion object {
        private var INSTANCE: LocalDataSourceTv? = null

        fun getInstance(tvDao: TvDao,favoriteTvDao: FavoriteTvDao): LocalDataSourceTv =
            INSTANCE ?: LocalDataSourceTv(tvDao,favoriteTvDao)
    }

    fun getAllTv(): Flow<List<TvEntity>> = tvDao.getAllTv()
    fun getTv(checkLogin:String):Flow<TvEntity> = tvDao.getTv(checkLogin)
    suspend fun insertTv(tv:List<TvEntity>) = tvDao.insertTv(tv)
    fun updateFilm(tv: TvEntity) = tvDao.updateTv(tv)

    fun getAllTvFavoritePagging(): Flow<List<FavoriteTvEntity>> = favoriteTvDao.getAllTvPagging()
    fun findTvFavorite(checkLogin:String):Flow<FavoriteTvEntity> = favoriteTvDao.findTv(checkLogin)
    fun insertTvFavorite(favorite: FavoriteTvEntity) = favoriteTvDao.insertTv(favorite)
    fun deleteTvFavorite(favorite: FavoriteTvEntity) = favoriteTvDao.deleteTv(favorite)
}