package com.example.movielisting.Services

import com.example.movielisting.AppConstant.ApiConstants
import com.example.movielisting.Model.MovieDetailResponseModel
import com.example.movielisting.Model.PopularMoviesResponseModel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET(ApiConstants.getPopularMovieEndPoint)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Header("Authorization") authToken: String,): Response<PopularMoviesResponseModel>

    @GET(ApiConstants.getMovieDetailEndPoint)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Header("Authorization") authToken: String,
    ): Response<MovieDetailResponseModel>
}
