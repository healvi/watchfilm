package com.healvimaginer.watchfilm.presentation.home.film

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.healvimaginer.watchfilm.core.domain.usecase.FilmUseCase
import com.healvimaginer.watchfilm.domain.usecase.FilmUseCase

class FilmViewModel(filmUseCase: FilmUseCase) :ViewModel() {
    val film = filmUseCase.getAllFilm().asLiveData()
}