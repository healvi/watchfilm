package com.healvimaginer.watchfilm.presentation.detail.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.healvimaginer.watchfilm.core.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.core.data.vo.Resource
import com.healvimaginer.watchfilm.core.domain.model.Film
import com.healvimaginer.watchfilm.core.domain.usecase.FilmUseCase

class DetailFilmViewModel(private val filmUseCase: FilmUseCase) :ViewModel() {
    private lateinit var contentId : String

    fun setSelectedFilm(contentId :String){
        this.contentId = contentId
    }
    fun getFilm() : LiveData<Resource<Film>> = filmUseCase.getFilm(contentId).asLiveData()

    fun insert(favoriteFilmEntity: Film) {
        filmUseCase.insert(favoriteFilmEntity)
    }

    fun delete(favoriteFilmEntity: Film) {
        filmUseCase.delete(favoriteFilmEntity)
    }

    fun findFilm(check:String) : LiveData<FavoriteFilmEntity> {
        return filmUseCase.findFilm(check).asLiveData()
    }
}