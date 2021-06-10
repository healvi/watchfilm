package com.healvimaginer.watchfilm.presentation.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.domain.model.Film
import com.healvimaginer.watchfilm.domain.usecase.FilmUseCase
import com.healvimaginer.watchfilm.domain.utils.vo.Resource
class FilmViewModel(filmUseCase: FilmUseCase) :ViewModel() {
    val film = filmUseCase.getAllFilm()
}