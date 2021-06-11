package com.healvimaginer.watchfilm.presentation.home.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.healvimaginer.watchfilm.domain.usecase.TvUseCase

class TvViewModel(tvUseCase: TvUseCase) :ViewModel() {
    val tv = tvUseCase.getAllTv().asLiveData()
}