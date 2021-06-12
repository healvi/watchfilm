package com.healvimaginer.watchfilm.favorite.di


import com.healvimaginer.watchfilm.favorite.filmtvfavorite.film.FavFilmViewModel
import com.healvimaginer.watchfilm.favorite.filmtvfavorite.tv.FavTvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvFavModelModule = module {
    viewModel { FavTvViewModel(get()) }
}

val filmFavModelModule = module {
    viewModel { FavFilmViewModel(get()) }
}