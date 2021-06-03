package com.healvimaginer.watchfilm.domain.viewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.domain.di.Injection
import com.healvimaginer.watchfilm.presentation.film.DetailFilmViewModel
import com.healvimaginer.watchfilm.presentation.film.FilmViewModel

class ViewModelFactory private constructor(private val mFilmRepository: FilmRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FilmViewModel::class.java) -> {
                FilmViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(DetailFilmViewModel::class.java) -> DetailFilmViewModel(mFilmRepository) as T

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}