package com.healvimaginer.watchfilm.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.domain.model.Tv
import com.healvimaginer.watchfilm.domain.vo.Resource

interface TvDataSource {
    fun getAllTv(): LiveData<Resource<List<Tv>>>
    fun getTv(TvId: String): LiveData<Resource<Tv>>
    fun getAllTvPagging() : LiveData<List<Tv>>
    fun insert(favoriteTvEntity: Tv)
    fun delete(favoriteTvEntity: Tv)
    fun findTv(checklogin : String) : LiveData<FavoriteTvEntity>
}