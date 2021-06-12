package com.healvimaginer.watchfilm.core.data.source.remote.network

import com.healvimaginer.watchfilm.data.source.remote.response.DetailMovieResponse
import com.healvimaginer.watchfilm.data.source.remote.response.DetailTvResponse
import com.healvimaginer.watchfilm.data.source.remote.response.ListMovieResponse
import com.healvimaginer.watchfilm.data.source.remote.response.ListTvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovies(
        @Query("api_key") apiKey: String? = "e1af1d8f71da53bab4ce522010bd47b0"
    ): ListMovieResponse

    @GET("tv/popular")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String? = "e1af1d8f71da53bab4ce522010bd47b0"
    ): ListTvResponse

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("api_key") apiKey: String? = "e1af1d8f71da53bab4ce522010bd47b0",
        @Query("query") query: String?
    ): DetailMovieResponse

    @GET("search/tv")
    suspend fun getSearchTvShows(
        @Query("api_key") apiKey: String? = "e1af1d8f71da53bab4ce522010bd47b0",
        @Query("query") query: String?
    ): DetailTvResponse
}
