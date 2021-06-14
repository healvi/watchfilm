package com.healvimaginer.watchfilm.core.domain.di

import androidx.room.Room
import com.healvimaginer.watchfilm.core.data.FilmRepository
import com.healvimaginer.watchfilm.core.data.TvRepository
import com.healvimaginer.watchfilm.core.data.source.RemoteDataSource
import com.healvimaginer.watchfilm.core.data.source.local.LocalDataSourceFilm
import com.healvimaginer.watchfilm.core.data.source.local.LocalDataSourceTv
import com.healvimaginer.watchfilm.core.data.source.local.room.database.FavoriteFilmDatabase
import com.healvimaginer.watchfilm.core.data.source.local.room.database.FavoriteTvDatabase
import com.healvimaginer.watchfilm.core.data.source.local.room.database.FilmDatabase
import com.healvimaginer.watchfilm.core.data.source.local.room.database.TvDatabase
import com.healvimaginer.watchfilm.core.data.source.remote.network.ApiService
import com.healvimaginer.watchfilm.core.domain.repository.IFilmRepository
import com.healvimaginer.watchfilm.core.domain.repository.ITvRepository
import com.healvimaginer.watchfilm.core.domain.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module{
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/iie1VXtL7HzAMF+/PVPR9xzT80kQxdZeJ+zduCB3uj0=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
        val passphrase : ByteArray = SQLiteDatabase.getBytes("healvi".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            TvDatabase::class.java, "Tv.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val databaseModuleFavTv = module {
    factory { get<FavoriteTvDatabase>().favoriteTvDao() }
    single {
        val passphrase : ByteArray = SQLiteDatabase.getBytes("healvi".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            FavoriteTvDatabase::class.java, "FavoriteTv.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()

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
        val passphrase : ByteArray = SQLiteDatabase.getBytes("healvi".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            FilmDatabase::class.java, "Film.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val databaseModuleFavFilm = module {
    factory { get<FavoriteFilmDatabase>().favoriteFilmDao() }
    single {
        val passphrase : ByteArray = SQLiteDatabase.getBytes("healvi".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            FavoriteFilmDatabase::class.java, "FavoriteFilm.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val repositoryModuleFilm = module(override = true) {
    single { RemoteDataSource(get()) }
    single { LocalDataSourceFilm(get(),get()) }
    factory { AppExecutors() }
    single<IFilmRepository> { FilmRepository(get(), get(), get()) }
}



