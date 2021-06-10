package com.healvimaginer.watchfilm.presentation.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.domain.model.Tv
import com.healvimaginer.watchfilm.domain.usecase.TvUseCase
import com.healvimaginer.watchfilm.domain.utils.vo.Resource

class DetailTvViewModel(private val tvUseCase: TvUseCase) :ViewModel() {
    private lateinit var contentId : String

    fun setSelectedTv(contentId :String) {
        this.contentId = contentId
    }

    fun getTv() : LiveData<Resource<Tv>> = tvUseCase.getTv(contentId)
    fun insert(favoriteTvEntity: Tv) {
        tvUseCase.insert(favoriteTvEntity)
    }

    fun delete(favoriteTvEntity: Tv) {
        tvUseCase.delete(favoriteTvEntity)
    }

    fun findTv(check:String) : LiveData<FavoriteTvEntity> {
        return tvUseCase.findTv(check)
    }
}