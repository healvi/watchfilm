package com.healvimaginer.watchfilm.presentation.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.domain.model.Tv
import com.healvimaginer.watchfilm.domain.vo.Resource

class TvViewModel(private val tvRepository: TvRepository) :ViewModel() {
    fun getTv() : LiveData<Resource<List<Tv>>> = tvRepository.getAllTv()
}