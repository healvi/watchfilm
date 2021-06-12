package com.healvimaginer.watchfilm.data.source

import android.util.Log
import com.healvimaginer.watchfilm.data.source.remote.network.ApiResponse
import com.healvimaginer.watchfilm.data.source.remote.network.ApiService
import com.healvimaginer.watchfilm.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllFilm() : Flow<ApiResponse<List<FilmResponse>>> {
        return flow {
           try {
               val response = apiService.getMovies("e1af1d8f71da53bab4ce522010bd47b0")
               val dataArray = response.results
               if (dataArray.isNotEmpty())  {
                   emit(ApiResponse.Success(response.results))
               } else {
                   emit(ApiResponse.Empty)
               }
           } catch (e : Exception) {
               emit(ApiResponse.Error(e.toString()))
               Log.e("Gagal", e.message.toString())
           }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTv() : Flow<ApiResponse<List<TvResponse>>> {
        return flow {
            try {
                val response = apiService.getTvShows("e1af1d8f71da53bab4ce522010bd47b0")
                val dataArray = response.results
                if (dataArray.isNotEmpty())  {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("Gagal film", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFilm(filmId:String) : Flow<ApiResponse<FilmResponse>> {
        return flow {
            try {
                val response = apiService.getSearchMovies("e1af1d8f71da53bab4ce522010bd47b0", filmId)
                val dataArray = response.results
                if (dataArray.contentId.isNotEmpty())  {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTv(tvId:String) : Flow<ApiResponse<TvResponse>> {
        return flow {
            try {
                val response = apiService.getSearchTvShows("e1af1d8f71da53bab4ce522010bd47b0", tvId)
                val dataArray = response.results
                if (dataArray.contentId.isNotEmpty())  {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}