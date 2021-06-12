package com.heavlimaginer.watchfilm.favorite.di

import com.healvimaginer.watchfilm.presentation.detail.film.DetailFilmViewModel
import com.healvimaginer.watchfilm.presentation.detail.tv.DetailTvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvFavModelModule = module {
    viewModel { DetailTvViewModel(get()) }
}

val filmFavModelModule = module {
    viewModel { DetailFilmViewModel(get()) }
}