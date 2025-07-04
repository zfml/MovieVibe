package com.zfml.movievibe.data.remote.api

import com.zfml.movievibe.BuildConfig
import com.zfml.movievibe.data.remote.model.MovieDto
import com.zfml.movievibe.data.remote.model.MovieResponse
import com.zfml.movievibe.util.Constants
import com.zfml.movievibe.util.Constants.MOVIE_END_POINT
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieResponse

}