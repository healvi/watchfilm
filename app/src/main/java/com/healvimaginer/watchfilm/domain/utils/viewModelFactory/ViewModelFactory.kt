package com.healvimaginer.watchfilm.domain.utils.viewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healvimaginer.watchfilm.domain.di.Injection
import com.healvimaginer.watchfilm.domain.usecase.FilmUseCase
import com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.film.FavFilmViewModel
import com.healvimaginer.watchfilm.presentation.detail.film.DetailFilmViewModel
import com.healvimaginer.watchfilm.presentation.home.film.FilmViewModel

class ViewModelFactory private constructor(private val filmUseCase: FilmUseCase) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.procideFilmUseCase(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FilmViewModel::class.java) -> {
                FilmViewModel(filmUseCase) as T
            }
            modelClass.isAssignableFrom(DetailFilmViewModel::class.java) -> {
                DetailFilmViewModel(filmUseCase) as T
            }
            modelClass.isAssignableFrom(FavFilmViewModel::class.java) -> {
                FavFilmViewModel(filmUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}