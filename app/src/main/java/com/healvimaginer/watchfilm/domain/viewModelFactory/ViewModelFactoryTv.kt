package com.healvimaginer.watchfilm.domain.viewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.domain.di.Injection
import com.healvimaginer.watchfilm.presentation.tv.DetailTvViewModel
import com.healvimaginer.watchfilm.presentation.tv.TvViewModel

class ViewModelFactoryTv private constructor(private val mTvRepository: TvRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactoryTv? = null

        fun getInstance(context: Context): ViewModelFactoryTv =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryTv(Injection.provideRepositoryTv(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(mTvRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvViewModel::class.java) -> DetailTvViewModel(mTvRepository) as T

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}