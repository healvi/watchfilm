package com.healvimaginer.watchfilm.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceTv
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.data.source.remote.ApiResponse
import com.healvimaginer.watchfilm.data.source.remote.NetworkBoundResource
import com.healvimaginer.watchfilm.data.source.remote.RemoteDataSource
import com.healvimaginer.watchfilm.data.source.remote.response.TvResponse
import com.healvimaginer.watchfilm.domain.utils.AppExecutors
import com.healvimaginer.watchfilm.domain.utils.vo.Resource
import java.util.*
import java.util.concurrent.Executors

class FakeTvRepository(private val remoteDataSource: RemoteDataSource, private val localDataSourceTv: LocalDataSourceTv, private val appExecutors: AppExecutors) : TvDataSource {
    val exe = Executors.newSingleThreadExecutor()
    override fun getAllTv(): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, List<TvResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(2)
                    .build()
                return LivePagedListBuilder(localDataSourceTv.getAllTv(),config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvResponse>>> {
                return remoteDataSource.getAllTv()
            }

            override fun saveCallResult(data: List<TvResponse>) {
                val tvList = ArrayList<TvEntity>()
                for (response in data) {
                    val tv = TvEntity(
                        response.contentId,
                        response.title,
                        response.description,
                        response.Kreator,
                        response.rilis,
                        response.image,
                        response.status,
                        response.network
                    )
                    tvList.add(tv)
                }
                localDataSourceTv.insertTv(tvList)
            }
        }.asLiveData()
    }

    override fun getTv(tvId: String): LiveData<Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, TvResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvEntity> {
                return localDataSourceTv.getTv(tvId)
            }

            override fun shouldFetch(data: TvEntity?): Boolean {
                return data?.contentId == null || data.contentId.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<TvResponse>> {
                return remoteDataSource.getTv(tvId)
            }

            override fun saveCallResult(response: TvResponse) {
                val tv = TvEntity(
                    response.contentId,
                    response.title,
                    response.description,
                    response.Kreator,
                    response.rilis,
                    response.image,
                    response.status,
                    response.network
                )
                localDataSourceTv.updateFilm(tv)
            }
        }.asLiveData()
    }

    override fun getAllTvPagging(): LiveData<PagedList<FavoriteTvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(2)
            .build()
        return LivePagedListBuilder(localDataSourceTv.getAllTvFavoritePagging(),config).build()
    }
    override fun insert(favoriteTvEntity: FavoriteTvEntity) {
        return exe.execute {
            localDataSourceTv.insertTvFavorite(favoriteTvEntity)
        }
    }
    override fun delete(favoriteTvEntity: FavoriteTvEntity) {
        return exe.execute {
            localDataSourceTv.deleteTvFavorite(favoriteTvEntity)
        }
    }
    override fun findTv(checklogin: String): LiveData<FavoriteTvEntity> = localDataSourceTv.findTvFavorite(checklogin)

}

