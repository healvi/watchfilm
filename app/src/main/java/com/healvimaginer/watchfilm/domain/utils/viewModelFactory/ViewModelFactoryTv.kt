package com.healvimaginer.watchfilm.domain.utils.viewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.domain.di.Injection
import com.healvimaginer.watchfilm.domain.usecase.TvUseCase
import com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.tv.FavTvViewModel
import com.healvimaginer.watchfilm.presentation.tv.DetailTvViewModel
import com.healvimaginer.watchfilm.presentation.tv.TvViewModel

class ViewModelFactoryTv private constructor(private val tvUseCase: TvUseCase) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactoryTv? = null

        fun getInstance(context: Context): ViewModelFactoryTv =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryTv(Injection.procideTvUseCase(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(tvUseCase) as T
            }
            modelClass.isAssignableFrom(DetailTvViewModel::class.java) -> {
                DetailTvViewModel(tvUseCase) as T
            }
            modelClass.isAssignableFrom(FavTvViewModel::class.java) -> {
                FavTvViewModel(tvUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}