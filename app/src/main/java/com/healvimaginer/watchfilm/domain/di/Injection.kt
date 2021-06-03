package com.healvimaginer.watchfilm.domain.di

import android.content.Context
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceFilm
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceTv
import com.healvimaginer.watchfilm.data.source.local.room.database.FilmDatabase
import com.healvimaginer.watchfilm.data.source.local.room.database.TvDatabase
import com.healvimaginer.watchfilm.data.source.remote.RemoteDataSource
import com.healvimaginer.watchfilm.domain.utils.AppExecutors
import com.healvimaginer.watchfilm.domain.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): FilmRepository {
        val database = FilmDatabase.getInstance(context)
        val localDataSourceFilm = LocalDataSourceFilm.getInstance(database.favoriteFilmDao())
        val appExecutors = AppExecutors()
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return FilmRepository.getInstance(remoteDataSource, localDataSourceFilm, appExecutors)
    }

    fun provideRepositoryTv(context: Context): TvRepository {
        val database = TvDatabase.getInstance(context)
        val localDataSourceFilm = LocalDataSourceTv.getInstance(database.favoriteTvDao())
        val appExecutors = AppExecutors()
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return TvRepository.getInstance(remoteDataSource,localDataSourceFilm,appExecutors)
    }
}