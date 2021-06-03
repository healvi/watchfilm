package com.healvimaginer.watchfilm.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.domain.vo.Resource

interface TvDataSource {
    fun getAllTv(): LiveData<Resource<PagedList<TvEntity>>>
    fun getTv(TvId: String): LiveData<Resource<TvEntity>>
}