package com.healvimaginer.watchfilm.core.domain.usecase.interactor

import com.healvimaginer.watchfilm.domain.model.Tv
import com.healvimaginer.watchfilm.domain.repository.ITvRepository
import com.healvimaginer.watchfilm.domain.usecase.TvUseCase

class TvInteractor(private val tvRepository: ITvRepository) : TvUseCase {
    override fun getAllTv() = tvRepository.getAllTv()

    override fun getTv(TvId: String) = tvRepository.getTv(TvId)

    override fun getAllTvPagging() = tvRepository.getAllTvPagging()

    override fun insert(favoriteTvEntity: Tv) = tvRepository.insert(favoriteTvEntity)

    override fun delete(favoriteTvEntity: Tv) = tvRepository.delete(favoriteTvEntity)

    override fun findTv(checklogin: String) = tvRepository.findTv(checklogin)

}