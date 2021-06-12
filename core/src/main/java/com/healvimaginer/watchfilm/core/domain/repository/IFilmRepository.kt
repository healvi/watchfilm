package com.healvimaginer.watchfilm.core.domain.repository

import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.domain.model.Film
import com.healvimaginer.watchfilm.data.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IFilmRepository {
    fun getAllFilm(): Flow<Resource<List<Film>>>
    fun getFilm(filmId: String): Flow<Resource<Film>>
    fun getAllFilmPagging() : Flow<List<Film>>
    fun insert(favoriteFilmEntity: Film)
    fun delete(favoriteFilmEntity: Film)
    fun findFilm(checklogin : String) : Flow<FavoriteFilmEntity>
}