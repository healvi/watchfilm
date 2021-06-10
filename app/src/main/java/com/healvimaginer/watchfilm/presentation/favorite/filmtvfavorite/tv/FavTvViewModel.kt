package com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.domain.model.Tv
import com.healvimaginer.watchfilm.domain.usecase.TvUseCase

class FavTvViewModel(tvUseCase: TvUseCase) :ViewModel() {
    val tv = tvUseCase.getAllTvPagging()
}