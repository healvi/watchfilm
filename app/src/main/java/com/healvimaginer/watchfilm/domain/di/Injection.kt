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
import com.healvimaginer.watchfilm.data.source.remote.RemoteDataSource
import com.healvimaginer.watchfilm.domain.utils.AppExecutors
import com.healvimaginer.watchfilm.domain.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): FilmRepository {
        val database = FilmDatabase.getInstance(context)
        val databaseFav = FavoriteFilmDatabase.getInstance(context)
        val localDataSourceFilm = LocalDataSourceFilm.getInstance(database.FilmDao(), databaseFav.favoriteFilmDao())
        val appExecutors = AppExecutors()
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return FilmRepository.getInstance(remoteDataSource, localDataSourceFilm, appExecutors)
    }

    fun provideRepositoryTv(context: Context): TvRepository {
        val database = TvDatabase.getInstance(context)
        val databaseFav = FavoriteTvDatabase.getInstance(context)
        val localDataSourceFilm = LocalDataSourceTv.getInstance(database.TvDao(), databaseFav.favoriteTvDao())
        val appExecutors = AppExecutors()
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return TvRepository.getInstance(remoteDataSource,localDataSourceFilm,appExecutors)
    }
}