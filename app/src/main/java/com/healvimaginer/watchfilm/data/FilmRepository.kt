package com.healvimaginer.watchfilm.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceFilm
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.remote.ApiResponse
import com.healvimaginer.watchfilm.data.source.remote.NetworkBoundResource
import com.healvimaginer.watchfilm.data.source.remote.RemoteDataSource
import com.healvimaginer.watchfilm.data.source.remote.response.FilmResponse
import com.healvimaginer.watchfilm.domain.utils.AppExecutors
import com.healvimaginer.watchfilm.domain.vo.Resource
import java.util.concurrent.Executors

class FilmRepository private constructor(private val remoteDataSource: RemoteDataSource, private val localDataSourceFilm: LocalDataSourceFilm, private val appExecutors: AppExecutors) :
    FilmDataSource {
    val exe = Executors.newSingleThreadExecutor()
    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(remoteData: RemoteDataSource,localDataSourceFilm: LocalDataSourceFilm,appExecutors: AppExecutors): FilmRepository =
            instance ?: synchronized(this) {
                FilmRepository(remoteData,localDataSourceFilm,appExecutors).apply { instance = this }
            }
    }

    override fun getAllFilm(): LiveData<Resource<PagedList<FilmsEntity>>> {
        return object : NetworkBoundResource<PagedList<FilmsEntity>, List<FilmResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<FilmsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(2)
                    .build()
                return LivePagedListBuilder(localDataSourceFilm.getAllFilm(),config).build()
            }

            override fun shouldFetch(data: PagedList<FilmsEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<FilmResponse>>> {
                return remoteDataSource.getAllFilm()
            }

            override fun saveCallResult(data: List<FilmResponse>) {
                val filmList = ArrayList<FilmsEntity>()
                for (response in data) {
                    val film = FilmsEntity(
                        response.contentId,
                        response.title,
                        response.description,
                        response.director,
                        response.rilis,
                        response.image,
                        response.anggaran,
                        response.pendapatan
                    )
                    filmList.add(film)
                }
                localDataSourceFilm.insertFilm(filmList)
            }
        }.asLiveData()
    }

    override fun getFilm(filmId: String): LiveData<Resource<FilmsEntity>> {
        return object : NetworkBoundResource<FilmsEntity, FilmResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<FilmsEntity> {
                return localDataSourceFilm.getFilm(filmId)
            }

            override fun shouldFetch(data: FilmsEntity?): Boolean {
                return data?.contentId == null || data.contentId.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<FilmResponse>> {
                return remoteDataSource.getFilm(filmId)
            }

            override fun saveCallResult(response: FilmResponse) {
                    val film = FilmsEntity(
                        response.contentId,
                        response.title,
                        response.description,
                        response.director,
                        response.rilis,
                        response.image,
                        response.anggaran,
                        response.pendapatan
                    )
                localDataSourceFilm.updateFilm(film)
            }
        }.asLiveData()
    }

    override fun getAllFilmPagging(): LiveData<PagedList<FavoriteFilmEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(2)
            .build()
        return LivePagedListBuilder(localDataSourceFilm.getAllFilmFavoritePagging(),config).build()
    }

    override fun insert(favoriteFilmEntity: FavoriteFilmEntity)  {
        return exe.execute {
            localDataSourceFilm.insertFilmFavorite(favoriteFilmEntity)
        }
    }

    override fun delete(favoriteFilmEntity: FavoriteFilmEntity) {
        return exe.execute {
            localDataSourceFilm.deleteFilmFavorite(favoriteFilmEntity)
        }
    }

    override fun findFilm(checklogin: String): LiveData<FavoriteFilmEntity> = localDataSourceFilm.findFilmFavorite(checklogin)


}
