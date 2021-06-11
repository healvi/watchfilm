package com.healvimaginer.watchfilm.data.source.remote.network

import com.healvimaginer.watchfilm.data.source.remote.response.DetailMovieResponse
import com.healvimaginer.watchfilm.data.source.remote.response.DetailTvResponse
import com.healvimaginer.watchfilm.data.source.remote.response.ListMovieResponse
import com.healvimaginer.watchfilm.data.source.remote.response.ListTvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun getMovies(
        @Query("api_key") apiKey: String? = "e1af1d8f71da53bab4ce522010bd47b0"
    ): Call<ListMovieResponse>

    @GET("tv/popular")
    fun getTvShows(
        @Query("api_key") apiKey: String? = "e1af1d8f71da53bab4ce522010bd47b0"
    ): Call<ListTvResponse>

    @GET("search/movie")
    fun getSearchMovies(
        @Query("api_key") apiKey: String? = "e1af1d8f71da53bab4ce522010bd47b0",
        @Query("query") query: String?
    ): Call<DetailMovieResponse>

    @GET("search/tv")
    fun getSearchTvShows(
        @Query("api_key") apiKey: String? = "e1af1d8f71da53bab4ce522010bd47b0",
        @Query("query") query: String?
    ): Call<DetailTvResponse>
}
