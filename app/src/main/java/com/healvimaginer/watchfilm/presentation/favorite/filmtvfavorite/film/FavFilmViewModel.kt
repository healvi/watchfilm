package com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.film

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.healvimaginer.watchfilm.core.domain.usecase.FilmUseCase

class FavFilmViewModel(filmUseCase: FilmUseCase) :ViewModel() {
    val film = filmUseCase.getAllFilmPagging().asLiveData()
}