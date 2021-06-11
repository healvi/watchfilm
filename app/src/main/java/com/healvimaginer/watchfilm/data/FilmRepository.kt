package com.healvimaginer.watchfilm.data

import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceFilm
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.RemoteDataSource
import com.healvimaginer.watchfilm.data.source.remote.network.ApiResponse
import com.healvimaginer.watchfilm.data.source.remote.response.FilmResponse
import com.healvimaginer.watchfilm.domain.model.Film
import com.healvimaginer.watchfilm.domain.repository.IFilmRepository
import com.healvimaginer.watchfilm.domain.utils.AppExecutors
import com.healvimaginer.watchfilm.domain.utils.DataMapper
import com.healvimaginer.watchfilm.data.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FilmRepository(private val remoteDataSource: RemoteDataSource, private val localDataSourceFilm: LocalDataSourceFilm, private val appExecutors: AppExecutors) :
    IFilmRepository {
    private val exe: ExecutorService = Executors.newSingleThreadExecutor()
    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localDataSourceFilm: LocalDataSourceFilm, appExecutors: AppExecutors): FilmRepository =
            instance ?: synchronized(this) {
                FilmRepository(remoteData,localDataSourceFilm,appExecutors).apply { instance = this }
            }
    }

    override fun getAllFilm(): Flow<Resource<List<Film>>> {
        return object : NetworkBoundResource<List<Film>, List<FilmResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Film>> {
                return localDataSourceFilm.getAllFilm().map {
                    DataMapper.mapEntitiesToDomainFilm(it)
                }
            }

            override fun shouldFetch(data: List<Film>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<FilmResponse>>> {
                return remoteDataSource.getAllFilm()
            }

            override suspend fun saveCallResult(data: List<FilmResponse>) {
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
        }.asFlow()
    }

    override fun getFilm(filmId: String): Flow<Resource<Film>> {
        return object : NetworkBoundResource<Film, FilmResponse>(appExecutors) {
            override fun loadFromDB(): Flow<Film> {
                return localDataSourceFilm.getFilm(filmId).map {
                    DataMapper.mapEntitiesToDomainFilmOne(it)
                }
            }

            override fun shouldFetch(data: Film?): Boolean {
                return data?.contentId == null || data.contentId.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<FilmResponse>> {
                return remoteDataSource.getFilm(filmId)
            }

            override suspend fun saveCallResult(data: FilmResponse) {
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
        }.asFlow()
    }

    override fun getAllFilmPagging(): Flow<List<Film>> {
        return localDataSourceFilm.getAllFilmFavoritePagging().map {
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

    override fun findFilm(checklogin: String): Flow<FavoriteFilmEntity> = localDataSourceFilm.findFilmFavorite(checklogin)


}
