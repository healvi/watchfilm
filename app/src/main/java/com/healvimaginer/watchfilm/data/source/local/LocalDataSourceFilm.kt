package com.healvimaginer.watchfilm.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.FavoriteFilmDao
import com.healvimaginer.watchfilm.data.source.local.room.dao.FilmDao

class LocalDataSourceFilm(private val filmDao: FilmDao, private val favoriteFilmDao: FavoriteFilmDao) {
    companion object {
        private var INSTANCE: LocalDataSourceFilm? = null

        fun getInstance(filmDao: FilmDao,favoriteFilmDao: FavoriteFilmDao): LocalDataSourceFilm =
            INSTANCE ?: LocalDataSourceFilm(filmDao, favoriteFilmDao)
    }

    fun getAllFilm(): LiveData<List<FilmsEntity>> = filmDao.getAllFilm()
    fun insertFilm(film: List<FilmsEntity>) = filmDao.insertFilm(film)
    fun getFilm(checkLogin:String): LiveData<FilmsEntity> = filmDao.getFilm(checkLogin)
    fun updateFilm(film: FilmsEntity) = filmDao.updateFilm(film)

    fun getAllFilmFavoritePagging(): LiveData<List<FavoriteFilmEntity>> = favoriteFilmDao.getAllFilmPagging()
    fun findFilmFavorite(checkLogin:String): LiveData<FavoriteFilmEntity> = favoriteFilmDao.findFilm(checkLogin)
    fun insertFilmFavorite(favorite: FavoriteFilmEntity) = favoriteFilmDao.insertFilm(favorite)
    fun deleteFilmFavorite(favorite: FavoriteFilmEntity) = favoriteFilmDao.deleteFilm(favorite)
}