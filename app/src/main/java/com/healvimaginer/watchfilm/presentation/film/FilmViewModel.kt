package com.healvimaginer.watchfilm.presentation.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.domain.vo.Resource
class FilmViewModel(private val filmRepository: FilmRepository) :ViewModel() {
    fun getFilm() : LiveData<Resource<PagedList<FilmsEntity>>> = filmRepository.getAllFilm()
}