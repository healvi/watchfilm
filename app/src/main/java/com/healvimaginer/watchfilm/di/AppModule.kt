package com.healvimaginer.watchfilm.di


import com.healvimaginer.watchfilm.core.domain.usecase.FilmUseCase
import com.healvimaginer.watchfilm.core.domain.usecase.TvUseCase
import com.healvimaginer.watchfilm.core.domain.usecase.interactor.FilmInteractor
import com.healvimaginer.watchfilm.core.domain.usecase.interactor.TvInteractor
import com.healvimaginer.watchfilm.presentation.detail.film.DetailFilmViewModel
import com.healvimaginer.watchfilm.presentation.detail.tv.DetailTvViewModel
import com.healvimaginer.watchfilm.presentation.home.film.FilmViewModel
import com.healvimaginer.watchfilm.presentation.home.tv.TvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filmCaseModule = module {
    factory<FilmUseCase> { FilmInteractor(get()) }
}

val filmModelModule = module {
    viewModel { FilmViewModel(get()) }
//    viewModel { FavFilmViewModel(get()) }
    viewModel { DetailFilmViewModel(get()) }
}

val tvCaseModule = module {
    factory<TvUseCase> { TvInteractor(get()) }
}

val tvModelModule = module {
    viewModel { TvViewModel(get()) }
//    viewModel { FavTvViewModel(get()) }
    viewModel { DetailTvViewModel(get()) }
}