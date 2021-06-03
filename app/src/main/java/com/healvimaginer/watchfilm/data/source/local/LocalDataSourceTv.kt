package com.healvimaginer.watchfilm.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.FilmDao
import com.healvimaginer.watchfilm.data.source.local.room.dao.TvDao

class LocalDataSourceTv(private val tvDao: TvDao) {
    companion object {
        private var INSTANCE: LocalDataSourceTv? = null

        fun getInstance(tvDao: TvDao): LocalDataSourceTv =
            INSTANCE ?: LocalDataSourceTv(tvDao)
    }

    fun getAllTv(): DataSource.Factory<Int, TvEntity> = tvDao.getAllTv()
    fun getTv(checkLogin:String):LiveData<TvEntity> = tvDao.getTv(checkLogin)
    fun insertTv(tv:List<TvEntity>) = tvDao.insertTv(tv)
    fun updateFilm(tv: TvEntity) = tvDao.updateTv(tv)
}