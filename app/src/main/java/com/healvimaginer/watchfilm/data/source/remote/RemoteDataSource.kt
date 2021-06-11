package com.healvimaginer.watchfilm.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.healvimaginer.watchfilm.data.source.remote.network.ApiResponse
import com.healvimaginer.watchfilm.data.source.remote.network.ApiService
import com.healvimaginer.watchfilm.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class RemoteDataSource private constructor(private val apiService: ApiService) {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(apiService).apply { instance = this }
            }
    }


    fun getAllFilm() : LiveData<ApiResponse<List<FilmResponse>>> {
        val resultFilm = MutableLiveData<ApiResponse<List<FilmResponse>>>()
        val client = apiService.getMovies("e1af1d8f71da53bab4ce522010bd47b0")
        client.enqueue(object : Callback<ListMovieResponse> {
            override fun onResponse(
                call: Call<ListMovieResponse>,
                response: Response<ListMovieResponse>
            ) {
                val dataArray = response.body()?.results
                resultFilm.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                resultFilm.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultFilm
    }

    fun getAllTv() : LiveData<ApiResponse<List<TvResponse>>> {
        val resultTv = MutableLiveData<ApiResponse<List<TvResponse>>>()
        val client = apiService.getTvShows("e1af1d8f71da53bab4ce522010bd47b0")
        client.enqueue(object : Callback<ListTvResponse> {
            override fun onResponse(
                call: Call<ListTvResponse>,
                response: Response<ListTvResponse>
            ) {
                val dataArray = response.body()?.results
                resultTv.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<ListTvResponse>, t: Throwable) {
                resultTv.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultTv
    }

    fun getFilm(filmId:String) : LiveData<ApiResponse<FilmResponse>> {
        val resultFilm = MutableLiveData<ApiResponse<FilmResponse>>()
        val client = apiService.getSearchMovies("e1af1d8f71da53bab4ce522010bd47b0", filmId)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                val data = response.body()?.results
                resultFilm.value = if (data != null) ApiResponse.Success(data) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                resultFilm.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultFilm
    }

    fun getTv(tvId:String) : LiveData<ApiResponse<TvResponse>> {
        val resultTv = MutableLiveData<ApiResponse<TvResponse>>()
        val client = apiService.getSearchTvShows("e1af1d8f71da53bab4ce522010bd47b0", tvId)
        client.enqueue(object : Callback<DetailTvResponse> {
            override fun onResponse(
                call: Call<DetailTvResponse>,
                response: Response<DetailTvResponse>
            ) {
                val data = response.body()?.results
                resultTv.value = if (data != null) ApiResponse.Success(data) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                resultTv.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultTv
    }
}