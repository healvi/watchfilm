package com.healvimaginer.watchfilm.core.data.source.local

import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.local.room.dao.FavoriteFilmDao
import com.healvimaginer.watchfilm.data.source.local.room.dao.FilmDao
import kotlinx.coroutines.flow.Flow

class LocalDataSourceFilm(private val filmDao: FilmDao, private val favoriteFilmDao: FavoriteFilmDao) {
//    companion object {
//        private var INSTANCE: LocalDataSourceFilm? = null
//
//        fun getInstance(filmDao: FilmDao,favoriteFilmDao: FavoriteFilmDao): LocalDataSourceFilm =
//            INSTANCE ?: LocalDataSourceFilm(filmDao, favoriteFilmDao)
//    }

    fun getAllFilm(): Flow<List<FilmsEntity>> = filmDao.getAllFilm()
    suspend fun insertFilm(film: List<FilmsEntity>) = filmDao.insertFilm(film)
    fun getFilm(checkLogin:String): Flow<FilmsEntity> = filmDao.getFilm(checkLogin)
    fun updateFilm(film: FilmsEntity) = filmDao.updateFilm(film)

    fun getAllFilmFavoritePagging(): Flow<List<FavoriteFilmEntity>> = favoriteFilmDao.getAllFilmPagging()
    fun findFilmFavorite(checkLogin:String): Flow<FavoriteFilmEntity> = favoriteFilmDao.findFilm(checkLogin)
    fun insertFilmFavorite(favorite: FavoriteFilmEntity) = favoriteFilmDao.insertFilm(favorite)
    fun deleteFilmFavorite(favorite: FavoriteFilmEntity) = favoriteFilmDao.deleteFilm(favorite)
}