package com.healvimaginer.watchfilm.data

import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceTv
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.data.source.RemoteDataSource
import com.healvimaginer.watchfilm.data.source.remote.network.ApiResponse
import com.healvimaginer.watchfilm.data.source.remote.response.TvResponse
import com.healvimaginer.watchfilm.domain.model.Tv
import com.healvimaginer.watchfilm.domain.repository.ITvRepository
import com.healvimaginer.watchfilm.domain.utils.AppExecutors
import com.healvimaginer.watchfilm.domain.utils.DataMapper
import com.healvimaginer.watchfilm.data.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TvRepository(private val remoteDataSource: RemoteDataSource, private val localDataSourceTv: LocalDataSourceTv, private val appExecutors: AppExecutors) : ITvRepository{
    private val exe: ExecutorService = Executors.newSingleThreadExecutor()
    companion object {
        @Volatile
        private var instance: TvRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localDataSourceTv: LocalDataSourceTv, appExecutors: AppExecutors): TvRepository =
            instance ?: synchronized(this) {
                TvRepository(remoteData,localDataSourceTv,appExecutors).apply { instance = this }
            }
    }

    override fun getAllTv(): Flow<Resource<List<Tv>>> {
        return object : NetworkBoundResource<List<Tv>, List<TvResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Tv>> {
                return localDataSourceTv.getAllTv().map {
                    DataMapper.mapEntitiesToDomainTv(it)
                }

            }

            override fun shouldFetch(data: List<Tv>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvResponse>>> {
                return remoteDataSource.getAllTv()
            }

            override suspend fun saveCallResult(data: List<TvResponse>) {
                val tvList = ArrayList<TvEntity>()
                for (it in data) {
                    val tv = TvEntity(
                        contentId=it.contentId,
                        name=it.name,
                        overview=it.overview,
                        popularity=it.popularity,
                        poster_path=it.poster_path,
                        backdrop_path=it.backdrop_path,
                        vote_average=it.vote_average,
                        first_air_date=it.first_air_date
                    )
                    tvList.add(tv)
                }
                localDataSourceTv.insertTv(tvList)
            }
        }.asFlow()
    }

    override fun getTv(TvId: String): Flow<Resource<Tv>> {
        return object : NetworkBoundResource<Tv, TvResponse>(appExecutors) {
            override fun loadFromDB(): Flow<Tv> {
                return localDataSourceTv.getTv(TvId).map {
                    DataMapper.mapEntitiesToDomainTvOne(it)
                }

            }

            override fun shouldFetch(data: Tv?): Boolean {
                return data?.contentId == null || data.contentId.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<TvResponse>> {
                return remoteDataSource.getTv(TvId)
            }

            override suspend fun saveCallResult(data: TvResponse) {
                val tv = TvEntity(
                    contentId=data.contentId,
                    name=data.name,
                    overview=data.overview,
                    popularity=data.popularity,
                    poster_path=data.poster_path,
                    backdrop_path=data.backdrop_path,
                    vote_average=data.vote_average,
                    first_air_date=data.first_air_date
                )
                localDataSourceTv.updateFilm(tv)
            }
        }.asFlow()
    }

    override fun getAllTvPagging(): Flow<List<Tv>> {
        return localDataSourceTv.getAllTvFavoritePagging().map {
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
    override fun findTv(checklogin: String): Flow<FavoriteTvEntity> = localDataSourceTv.findTvFavorite(checklogin)
}
