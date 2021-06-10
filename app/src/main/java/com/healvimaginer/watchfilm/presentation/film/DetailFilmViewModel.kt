package com.healvimaginer.watchfilm.presentation.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.domain.model.Film
import com.healvimaginer.watchfilm.domain.usecase.FilmUseCase
import com.healvimaginer.watchfilm.domain.utils.vo.Resource

class DetailFilmViewModel(private val filmUseCase: FilmUseCase) :ViewModel() {
    private lateinit var contentId : String

    fun setSelectedFilm(contentId :String){
        this.contentId = contentId
    }
    fun getFilm() : LiveData<Resource<Film>> = filmUseCase.getFilm(contentId)

    fun insert(favoriteFilmEntity: Film) {
        filmUseCase.insert(favoriteFilmEntity)
    }

    fun delete(favoriteFilmEntity: Film) {
        filmUseCase.delete(favoriteFilmEntity)
    }

    fun findFilm(check:String) : LiveData<FavoriteFilmEntity> {
        return filmUseCase.findFilm(check)
    }
}