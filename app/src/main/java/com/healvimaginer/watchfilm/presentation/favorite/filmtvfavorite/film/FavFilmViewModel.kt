package com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity

class FavFilmViewModel(private val filmRepository: FilmRepository) :ViewModel() {
    fun getAllFilmPag(): LiveData<PagedList<FavoriteFilmEntity>> = filmRepository.getAllFilmPagging()
}