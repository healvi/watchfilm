package com.healvimaginer.watchfilm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceTv
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.data.source.remote.ApiResponse
import com.healvimaginer.watchfilm.data.source.remote.NetworkBoundResource
import com.healvimaginer.watchfilm.data.source.remote.RemoteDataSource
import com.healvimaginer.watchfilm.data.source.remote.response.TvResponse
import com.healvimaginer.watchfilm.domain.model.Tv
import com.healvimaginer.watchfilm.domain.utils.AppExecutors
import com.healvimaginer.watchfilm.domain.utils.DataMapper
import com.healvimaginer.watchfilm.domain.vo.Resource
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TvRepository private constructor(private val remoteDataSource: RemoteDataSource, private val localDataSourceTv: LocalDataSourceTv, private val appExecutors: AppExecutors) :
    TvDataSource {
    private val exe: ExecutorService = Executors.newSingleThreadExecutor()
    companion object {
        @Volatile
        private var instance: TvRepository? = null

        fun getInstance(remoteData: RemoteDataSource,localDataSourceTv: LocalDataSourceTv,appExecutors: AppExecutors): TvRepository =
            instance ?: synchronized(this) {
                TvRepository(remoteData,localDataSourceTv,appExecutors).apply { instance = this }
            }
    }

    override fun getAllTv(): LiveData<Resource<List<Tv>>> {
        return object : NetworkBoundResource<List<Tv>, List<TvResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Tv>> {
                return Transformations.map(localDataSourceTv.getAllTv()) {
                    DataMapper.mapEntitiesToDomainTv(it)
                }

            }

            override fun shouldFetch(data: List<Tv>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvResponse>>> {
                return remoteDataSource.getAllTv()
            }

            override fun saveCallResult(data: List<TvResponse>) {
                val tvList = ArrayList<TvEntity>()
                for (it in data) {
                    val tv = TvEntity(
                        contentId=it.contentId,
                        title=it.title,
                        name=it.name,
                        overview=it.overview,
                        popularity=it.popularity,
                        poster_path=it.poster_path,
                        backdrop_path=it.backdrop_path,
                        vote_average=it.vote_average,
                        release_date=it.release_date,
                        first_air_date=it.first_air_date
                    )
                    tvList.add(tv)
                }
                localDataSourceTv.insertTv(tvList)
            }
        }.asLiveData()
    }

    override fun getTv(TvId: String): LiveData<Resource<Tv>> {
        return object : NetworkBoundResource<Tv, TvResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<Tv> {
                return Transformations.map(localDataSourceTv.getTv(TvId)) {
                    DataMapper.mapEntitiesToDomainTvOne(it)
                }

            }

            override fun shouldFetch(data: Tv?): Boolean {
                return data?.contentId == null || data.contentId.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<TvResponse>> {
                return remoteDataSource.getTv(TvId)
            }

            override fun saveCallResult(data: TvResponse) {
                val tv = TvEntity(
                    contentId=data.contentId,
                    title=data.title,
                    name=data.name,
                    overview=data.overview,
                    popularity=data.popularity,
                    poster_path=data.poster_path,
                    backdrop_path=data.backdrop_path,
                    vote_average=data.vote_average,
                    release_date=data.release_date,
                    first_air_date=data.first_air_date
                )
                localDataSourceTv.updateFilm(tv)
            }
        }.asLiveData()
    }

    override fun getAllTvPagging(): LiveData<List<Tv>> {
        return Transformations.map(localDataSourceTv.getAllTvFavoritePagging()) {
            DataMapper.mapEntitiesToDomainFavTv(it)
        }
    }

    override fun insert(favoriteTvEntity: Tv) {
        return exe.execute {
            localDataSourceTv.insertTvFavorite(DataMapper.mapDomainToFavTvEntity(favoriteTvEntity))
        }
    }
    override fun delete(favoriteTvEntity: Tv) {
        return exe.execute {
            localDataSourceTv.deleteTvFavorite(DataMapper.mapDomainToFavTvEntity(favoriteTvEntity))
        }
    }
    override fun findTv(checklogin: String): LiveData<FavoriteTvEntity> = localDataSourceTv.findTvFavorite(checklogin)
}
