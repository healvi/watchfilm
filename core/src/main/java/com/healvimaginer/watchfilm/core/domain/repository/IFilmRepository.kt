package com.healvimaginer.watchfilm.core.domain.repository

import com.healvimaginer.watchfilm.core.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.core.data.vo.Resource
import com.healvimaginer.watchfilm.core.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface IFilmRepository {
    fun getAllFilm(): Flow<Resource<List<Film>>>
    fun getFilm(filmId: String): Flow<Resource<Film>>
    fun getAllFilmPagging() : Flow<List<Film>>
    fun insert(favoriteFilmEntity: Film)
    fun delete(favoriteFilmEntity: Film)
    fun findFilm(checklogin : String) : Flow<FavoriteFilmEntity>
}