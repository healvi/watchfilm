package com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity

class FavTvViewModel(private val tvRepository: TvRepository) :ViewModel() {
    fun getAllTvPag(): LiveData<PagedList<FavoriteTvEntity>> = tvRepository.getAllTvPagging()
}