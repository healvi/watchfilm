package com.healvimaginer.watchfilm.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.healvimaginer.watchfilm.data.source.remote.response.FilmResponse
import com.healvimaginer.watchfilm.data.source.remote.response.TvResponse
import com.healvimaginer.watchfilm.domain.utils.EspressoIdlingResource
import com.healvimaginer.watchfilm.domain.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(helper).apply { instance = this }
            }
    }


    fun getAllFilm() : LiveData<ApiResponse<List<FilmResponse>>> {
        EspressoIdlingResource.increment()
        val resultFilm = MutableLiveData<ApiResponse<List<FilmResponse>>>()
        handler.postDelayed({
            resultFilm.value = ApiResponse.success(jsonHelper.loadAllFilm())
            EspressoIdlingResource.decrement()
                            }, SERVICE_LATENCY_IN_MILLIS)
        return resultFilm
    }

    fun getAllTv() : LiveData<ApiResponse<List<TvResponse>>> {
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<List<TvResponse>>>()
        handler.postDelayed({
            resultTv.value = ApiResponse.success(jsonHelper.loadAllTv())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultTv
    }

    fun getFilm(filmId:String) : LiveData<ApiResponse<FilmResponse>> {
        EspressoIdlingResource.increment()
        val resultFilm = MutableLiveData<ApiResponse<FilmResponse>>()
        handler.postDelayed({
            resultFilm.value = ApiResponse.success(jsonHelper.loadFilm(filmId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultFilm
    }

    fun getTv(tvId:String) : LiveData<ApiResponse<TvResponse>> {
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<TvResponse>>()
        handler.postDelayed({
            resultTv.value = ApiResponse.success(jsonHelper.loadTv(tvId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultTv
    }
}