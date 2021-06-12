package com.healvimaginer.watchfilm.domain.di

import androidx.room.Room
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.data.source.RemoteDataSource
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceFilm
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceTv
import com.healvimaginer.watchfilm.data.source.local.room.database.FavoriteFilmDatabase
import com.healvimaginer.watchfilm.data.source.local.room.database.FavoriteTvDatabase
import com.healvimaginer.watchfilm.data.source.local.room.database.FilmDatabase
import com.healvimaginer.watchfilm.data.source.local.room.database.TvDatabase
import com.healvimaginer.watchfilm.data.source.remote.network.ApiService
import com.healvimaginer.watchfilm.domain.repository.IFilmRepository
import com.healvimaginer.watchfilm.domain.repository.ITvRepository
import com.healvimaginer.watchfilm.domain.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module{
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
val databaseModuleTv = module {
    factory { get<TvDatabase>().TvDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            TvDatabase::class.java, "Tv.db"
        ).fallbackToDestructiveMigration().build()
    }
}
val databaseModuleFavTv = module {
    factory { get<FavoriteTvDatabase>().favoriteTvDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FavoriteTvDatabase::class.java, "FavoriteTv.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModuleTv = module{
    single { RemoteDataSource(get()) }
    single { LocalDataSourceTv(get(),get()) }
    factory { AppExecutors() }
    single<ITvRepository> { TvRepository(get(), get(), get()) }
}
val databaseModuleFilm = module {
    factory { get<FilmDatabase>().FilmDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FilmDatabase::class.java, "Film.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val databaseModuleFavFilm = module {
    factory { get<FavoriteFilmDatabase>().favoriteFilmDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FavoriteFilmDatabase::class.java, "FavoriteFilm.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModuleFilm = module(override = true) {
    single { RemoteDataSource(get()) }
    single { LocalDataSourceFilm(get(),get()) }
    factory { AppExecutors() }
    single<IFilmRepository> { FilmRepository(get(), get(), get()) }
}



