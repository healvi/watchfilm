package com.healvimaginer.watchfilm.presentation.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.domain.model.Tv
import com.healvimaginer.watchfilm.domain.usecase.TvUseCase
import com.healvimaginer.watchfilm.domain.utils.vo.Resource

class TvViewModel(tvUseCase: TvUseCase) :ViewModel() {
    val tv = tvUseCase.getAllTv()
}