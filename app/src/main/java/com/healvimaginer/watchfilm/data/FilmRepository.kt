package com.healvimaginer.watchfilm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceFilm
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.remote.ApiResponses
import com.healvimaginer.watchfilm.data.source.remote.NetworkBoundResource
import com.healvimaginer.watchfilm.data.source.remote.RemoteDataSource
import com.healvimaginer.watchfilm.data.source.remote.network.ApiResponse
import com.healvimaginer.watchfilm.data.source.remote.response.FilmResponse
import com.healvimaginer.watchfilm.domain.model.Film
import com.healvimaginer.watchfilm.domain.repository.IFilmRepository
import com.healvimaginer.watchfilm.domain.utils.AppExecutors
import com.healvimaginer.watchfilm.domain.utils.DataMapper
import com.healvimaginer.watchfilm.domain.utils.vo.Resource
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FilmRepository private constructor(private val remoteDataSource: RemoteDataSource, private val localDataSourceFilm: LocalDataSourceFilm, private val appExecutors: AppExecutors) :
    IFilmRepository {
    private val exe: ExecutorService = Executors.newSingleThreadExecutor()
    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(remoteData: RemoteDataSource,localDataSourceFilm: LocalDataSourceFilm,appExecutors: AppExecutors): FilmRepository =
            instance ?: synchronized(this) {
                FilmRepository(remoteData,localDataSourceFilm,appExecutors).apply { instance = this }
            }
    }

    override fun getAllFilm(): LiveData<Resource<List<Film>>> {
        return object : NetworkBoundResource<List<Film>, List<FilmResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Film>> {
                return Transformations.map(localDataSourceFilm.getAllFilm()) {
                    DataMapper.mapEntitiesToDomainFilm(it)
                }
            }

            override fun shouldFetch(data: List<Film>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<FilmResponse>>> {
                return remoteDataSource.getAllFilm()
            }

            override fun saveCallResult(data: List<FilmResponse>) {
                val filmList = ArrayList<FilmsEntity>()
                for (it in data) {
                    val film = FilmsEntity(
                        contentId=it.contentId,
                        title=it.title,
                        overview=it.overview,
                        popularity=it.popularity,
                        poster_path=it.poster_path,
                        backdrop_path=it.backdrop_path,
                        vote_average=it.vote_average,
                        release_date=it.release_date,
                    )
                    filmList.add(film)
                }
                localDataSourceFilm.insertFilm(filmList)
            }
        }.asLiveData()
    }

    override fun getFilm(filmId: String): LiveData<Resource<Film>> {
        return object : NetworkBoundResource<Film, FilmResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<Film> {
                return Transformations.map(localDataSourceFilm.getFilm(filmId)) {
                    DataMapper.mapEntitiesToDomainFilmOne(it)
                }
            }

            override fun shouldFetch(data: Film?): Boolean {
                return data?.contentId == null || data.contentId.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<FilmResponse>> {
                return remoteDataSource.getFilm(filmId)
            }

            override fun saveCallResult(data: FilmResponse) {
                    val film = FilmsEntity(
                        contentId=data.contentId,
                        title=data.title,
                        overview=data.overview,
                        popularity=data.popularity,
                        poster_path=data.poster_path,
                        backdrop_path=data.backdrop_path,
                        vote_average=data.vote_average,
                        release_date=data.release_date,
                    )
                localDataSourceFilm.updateFilm(film)
            }
        }.asLiveData()
    }

    override fun getAllFilmPagging(): LiveData<List<Film>> {
        return Transformations.map(localDataSourceFilm.getAllFilmFavoritePagging()) {
            DataMapper.mapEntitiesToDomainFavFilm(it)
        }

    }

    override fun insert(favoriteFilmEntity: Film)  {
        return exe.execute {
            localDataSourceFilm.insertFilmFavorite(DataMapper.mapDomainToFavFilmEntity(favoriteFilmEntity))
        }
    }

    override fun delete(favoriteFilmEntity: Film) {
        return exe.execute {
            localDataSourceFilm.deleteFilmFavorite(DataMapper.mapDomainToFavFilmEntity(favoriteFilmEntity))
        }
    }

    override fun findFilm(checklogin: String): LiveData<FavoriteFilmEntity> = localDataSourceFilm.findFilmFavorite(checklogin)


}
