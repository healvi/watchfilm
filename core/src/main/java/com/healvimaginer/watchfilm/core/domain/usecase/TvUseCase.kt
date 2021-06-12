package com.healvimaginer.watchfilm.core.domain.usecase

import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.domain.model.Tv
import com.healvimaginer.watchfilm.data.vo.Resource
import kotlinx.coroutines.flow.Flow

interface TvUseCase {
    fun getAllTv(): Flow<Resource<List<Tv>>>
    fun getTv(TvId: String): Flow<Resource<Tv>>
    fun getAllTvPagging() : Flow<List<Tv>>
    fun insert(favoriteTvEntity: Tv)
    fun delete(favoriteTvEntity: Tv)
    fun findTv(checklogin : String) : Flow<FavoriteTvEntity>
}