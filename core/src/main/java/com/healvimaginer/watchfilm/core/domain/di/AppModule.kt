package com.healvimaginer.watchfilm.core.domain.di

import com.healvimaginer.watchfilm.domain.usecase.FilmUseCase
import com.healvimaginer.watchfilm.domain.usecase.TvUseCase
import com.healvimaginer.watchfilm.domain.usecase.interactor.FilmInteractor
import com.healvimaginer.watchfilm.domain.usecase.interactor.TvInteractor
import com.healvimaginer.watchfilm.presentation.detail.film.DetailFilmViewModel
import com.healvimaginer.watchfilm.presentation.detail.tv.DetailTvViewModel
import com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.film.FavFilmViewModel
import com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.tv.FavTvViewModel
import com.healvimaginer.watchfilm.presentation.home.film.FilmViewModel
import com.healvimaginer.watchfilm.presentation.home.tv.TvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filmCaseModule = module {
    factory<FilmUseCase> { FilmInteractor(get()) }
}

val filmModelModule = module {
    viewModel { FilmViewModel(get()) }
    viewModel { FavFilmViewModel(get()) }
    viewModel { DetailFilmViewModel(get()) }
}

val tvCaseModule = module {
    factory<TvUseCase> { TvInteractor(get()) }
}

val tvModelModule = module {
    viewModel { TvViewModel(get()) }
    viewModel { FavTvViewModel(get()) }
    viewModel { DetailTvViewModel(get()) }
}