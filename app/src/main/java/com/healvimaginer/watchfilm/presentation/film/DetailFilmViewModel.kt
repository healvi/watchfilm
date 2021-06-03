package com.healvimaginer.watchfilm.presentation.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.domain.vo.Resource

class DetailFilmViewModel(private val filmRepository: FilmRepository) :ViewModel() {
    private lateinit var contentId : String

    fun setSelectedFilm(contentId :String){
        this.contentId = contentId
    }
    fun getFilm() : LiveData<Resource<FilmsEntity>> = filmRepository.getFilm(contentId)
}