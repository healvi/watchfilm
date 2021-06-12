package com.healvimaginer.watchfilm.favorite.filmtvfavorite.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.healvimaginer.watchfilm.core.domain.usecase.TvUseCase

class FavTvViewModel(tvUseCase: TvUseCase) :ViewModel() {
    val tv = tvUseCase.getAllTvPagging().asLiveData()
}