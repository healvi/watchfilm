package com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.domain.model.Film
import com.healvimaginer.watchfilm.domain.usecase.FilmUseCase

class FavFilmViewModel(filmUseCase: FilmUseCase) :ViewModel() {
    val film = filmUseCase.getAllFilmPagging().asLiveData()
}