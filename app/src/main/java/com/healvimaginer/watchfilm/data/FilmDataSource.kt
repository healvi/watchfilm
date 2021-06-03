package com.healvimaginer.watchfilm.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity

import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.domain.vo.Resource

interface FilmDataSource {
    fun getAllFilm(): LiveData<Resource<PagedList<FilmsEntity>>>
    fun getFilm(filmId: String): LiveData<Resource<FilmsEntity>>
    fun getAllFilmPagging() : LiveData<PagedList<FavoriteFilmEntity>>
    fun insert(favoriteFilmEntity: FavoriteFilmEntity)
    fun delete(favoriteFilmEntity: FavoriteFilmEntity)
    fun findFilm(checklogin : String) :LiveData<FavoriteFilmEntity>
}