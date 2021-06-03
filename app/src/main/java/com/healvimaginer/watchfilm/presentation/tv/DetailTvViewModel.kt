package com.healvimaginer.watchfilm.presentation.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.domain.vo.Resource

class DetailTvViewModel(private val tvRepository: TvRepository) :ViewModel() {
    private lateinit var contentId : String

    fun setSelectedTv(contentId :String){
        this.contentId = contentId
    }

    fun getTv() : LiveData<Resource<TvEntity>> = tvRepository.getTv(contentId)
    fun insert(favoriteTvEntity: FavoriteTvEntity) {
        tvRepository.insert(favoriteTvEntity)
    }

    fun delete(favoriteTvEntity: FavoriteTvEntity) {
        tvRepository.delete(favoriteTvEntity)
    }

    fun findTv(check:String) : LiveData<FavoriteTvEntity> {
        return tvRepository.findTv(check)
    }
}