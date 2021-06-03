package com.healvimaginer.watchfilm.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.FilmDao

class LocalDataSourceFilm(private val filmDao: FilmDao) {
    companion object {
        private var INSTANCE: LocalDataSourceFilm? = null

        fun getInstance(filmDao: FilmDao): LocalDataSourceFilm =
            INSTANCE ?: LocalDataSourceFilm(filmDao)
    }

    fun getAllFilm(): DataSource.Factory<Int, FilmsEntity> = filmDao.getAllFilm()
    fun insertFilm(film: List<FilmsEntity>) = filmDao.insertFilm(film)
    fun getFilm(checkLogin:String): LiveData<FilmsEntity> = filmDao.getFilm(checkLogin)
    fun updateFilm(film: FilmsEntity) = filmDao.updateFilm(film)

}