package com.healvimaginer.watchfilm.core.data

import com.healvimaginer.watchfilm.core.data.source.RemoteDataSource
import com.healvimaginer.watchfilm.core.data.source.local.LocalDataSourceFilm
import com.healvimaginer.watchfilm.core.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.core.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.core.data.source.remote.network.ApiResponse
import com.healvimaginer.watchfilm.core.data.source.remote.response.FilmResponse
import com.healvimaginer.watchfilm.core.data.vo.Resource
import com.healvimaginer.watchfilm.core.domain.model.Film
import com.healvimaginer.watchfilm.core.domain.repository.IFilmRepository
import com.healvimaginer.watchfilm.core.domain.utils.AppExecutors
import com.healvimaginer.watchfilm.core.domain.utils.DataMapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FilmRepository(private val remoteDataSource: RemoteDataSource, private val localDataSourceFilm: LocalDataSourceFilm, private val appExecutors: AppExecutors) :
    IFilmRepository {
    private val exe: ExecutorService = Executors.newSingleThreadExecutor()
    companion object {
        @Volatile
        private var instance: com.healvimaginer.watchfilm.core.data.FilmRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localDataSourceFilm: LocalDataSourceFilm, appExecutors: AppExecutors): com.healvimaginer.watchfilm.core.data.FilmRepository =
            com.healvimaginer.watchfilm.core.data.FilmRepository.Companion.instance ?: synchronized(this) {
                com.healvimaginer.watchfilm.core.data.FilmRepository(
                    remoteData,
                    localDataSourceFilm,
                    appExecutors
                ).apply { com.healvimaginer.watchfilm.core.data.FilmRepository.Companion.instance = this }
            }
    }

    override fun getAllFilm(): Flow<Resource<List<Film>>> {
        return object : com.healvimaginer.watchfilm.core.data.NetworkBoundResource<List<Film>, List<FilmResponse>>(appExecutors) {
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
        return object : com.healvimaginer.watchfilm.core.data.NetworkBoundResource<Film, FilmResponse>(appExecutors) {
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
