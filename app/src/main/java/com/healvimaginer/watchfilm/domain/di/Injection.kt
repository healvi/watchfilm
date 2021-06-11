package com.healvimaginer.watchfilm.domain.di

import android.content.Context
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceFilm
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceTv
import com.healvimaginer.watchfilm.data.source.local.room.database.FavoriteFilmDatabase
import com.healvimaginer.watchfilm.data.source.local.room.database.FavoriteTvDatabase
import com.healvimaginer.watchfilm.data.source.local.room.database.FilmDatabase
import com.healvimaginer.watchfilm.data.source.local.room.database.TvDatabase
import com.healvimaginer.watchfilm.data.source.RemoteDataSource
import com.healvimaginer.watchfilm.data.source.remote.network.ApiConfig
import com.healvimaginer.watchfilm.domain.repository.IFilmRepository
import com.healvimaginer.watchfilm.domain.repository.ITvRepository
import com.healvimaginer.watchfilm.domain.usecase.FilmUseCase
import com.healvimaginer.watchfilm.domain.usecase.TvUseCase
import com.healvimaginer.watchfilm.domain.usecase.interactor.FilmInteractor
import com.healvimaginer.watchfilm.domain.usecase.interactor.TvInteractor
import com.healvimaginer.watchfilm.domain.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IFilmRepository {
        val database = FilmDatabase.getInstance(context)
        val databaseFav = FavoriteFilmDatabase.getInstance(context)
        val localDataSourceFilm = LocalDataSourceFilm.getInstance(database.FilmDao(), databaseFav.favoriteFilmDao())
        val appExecutors = AppExecutors()
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())

        return FilmRepository.getInstance(remoteDataSource, localDataSourceFilm, appExecutors)
    }

    fun procideFilmUseCase(context: Context):FilmUseCase {
        val repository = provideRepository(context)
        return FilmInteractor(repository)
    }

    private fun provideRepositoryTv(context: Context): ITvRepository {
        val database = TvDatabase.getInstance(context)
        val databaseFav = FavoriteTvDatabase.getInstance(context)
        val localDataSourceFilm = LocalDataSourceTv.getInstance(database.TvDao(), databaseFav.favoriteTvDao())
        val appExecutors = AppExecutors()
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())

        return TvRepository.getInstance(remoteDataSource,localDataSourceFilm,appExecutors)
    }

    fun procideTvUseCase(context: Context): TvUseCase {
        val repository = provideRepositoryTv(context)
        return TvInteractor(repository)
    }
}