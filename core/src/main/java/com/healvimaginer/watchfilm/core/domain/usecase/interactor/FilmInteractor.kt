package com.healvimaginer.watchfilm.core.domain.usecase.interactor

import com.healvimaginer.watchfilm.core.domain.model.Film
import com.healvimaginer.watchfilm.core.domain.repository.IFilmRepository
import com.healvimaginer.watchfilm.core.domain.usecase.FilmUseCase


class FilmInteractor(private val filmRepository: IFilmRepository) : FilmUseCase {
    override fun getAllFilm() = filmRepository.getAllFilm()

    override fun getFilm(filmId: String) = filmRepository.getFilm(filmId)

    override fun getAllFilmPagging() = filmRepository.getAllFilmPagging()

    override fun insert(favoriteFilmEntity: Film) = filmRepository.insert(favoriteFilmEntity)

    override fun delete(favoriteFilmEntity: Film) = filmRepository.delete(favoriteFilmEntity)

    override fun findFilm(checklogin: String) = filmRepository.findFilm(checklogin)
}