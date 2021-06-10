package com.healvimaginer.watchfilm.domain.usecase

import androidx.lifecycle.LiveData
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.domain.model.Tv
import com.healvimaginer.watchfilm.domain.utils.vo.Resource

interface TvUseCase {
    fun getAllTv(): LiveData<Resource<List<Tv>>>
    fun getTv(TvId: String): LiveData<Resource<Tv>>
    fun getAllTvPagging() : LiveData<List<Tv>>
    fun insert(favoriteTvEntity: Tv)
    fun delete(favoriteTvEntity: Tv)
    fun findTv(checklogin : String) : LiveData<FavoriteTvEntity>
}