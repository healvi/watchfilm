package com.healvimaginer.watchfilm.domain.usecase

import androidx.lifecycle.LiveData
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.domain.model.Film
import com.healvimaginer.watchfilm.domain.utils.vo.Resource

interface FilmUseCase {
    fun getAllFilm(): LiveData<Resource<List<Film>>>
    fun getFilm(filmId: String): LiveData<Resource<Film>>
    fun getAllFilmPagging() : LiveData<List<Film>>
    fun insert(favoriteFilmEntity: Film)
    fun delete(favoriteFilmEntity: Film)
    fun findFilm(checklogin : String) : LiveData<FavoriteFilmEntity>
}