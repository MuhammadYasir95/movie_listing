package com.example.movielisting.Controller

import android.content.Context
import com.example.movielisting.AppConstant.ApiConstants
import com.example.movielisting.Helper.AppModel
import com.example.movielisting.Model.MovieDetailResponseModel
import com.example.movielisting.Model.PopularMoviesResponseModel
import com.example.movielisting.Services.MovieApiClient
import com.example.movielisting.Services.MovieApiService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MovieController {

    private val movieApiService = MovieApiClient.retrofit.create(MovieApiService::class.java)

    @OptIn(ExperimentalSerializationApi::class)
    suspend fun getPopularMovies(applicationContext: Context): PopularMoviesResponseModel? {
        try{
            if (AppModel.isInternetConnected(applicationContext)) {
                val response =
                    movieApiService.getPopularMovies(ApiConstants.apiKey, ApiConstants.authToken)
                if (response.isSuccessful) {
                    val movieDetailsJson = Json.encodeToString(response.body())

                    val sharedPreferences =
                        applicationContext.getSharedPreferences("MoviesPref", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("movie_popular", movieDetailsJson)
                    editor.apply()

                    return response.body()
                }
            } else {
                return getPopularMovieFromSharedPreferences(applicationContext)
            }
            return null
        }catch (e: Exception){
            println(e)
        }
        return null

    }

    @OptIn(ExperimentalSerializationApi::class)
    suspend fun getMovieDetails(movieId: Int, applicationContext: Context): MovieDetailResponseModel? {

        try{
            if (AppModel.isInternetConnected(applicationContext)) {
                val response = movieApiService.getMovieDetails(
                    movieId,
                    ApiConstants.apiKey,
                    ApiConstants.authToken
                )

                if (response.isSuccessful) {
                    val movieDetailsJson = Json.encodeToString(response.body())

                    val sharedPreferences =
                        applicationContext.getSharedPreferences("MoviesPref", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("movie_details", movieDetailsJson)
                    editor.apply()

                    return response.body()
                }
            } else {
                return getMovieDetailsFromSharedPreferences(applicationContext)
            }
            return null
        }catch (e: Exception){
            println(e)
        }
        return null
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getPopularMovieFromSharedPreferences(context: Context): PopularMoviesResponseModel? {
        val sharedPreferences = context.getSharedPreferences("MoviesPref", Context.MODE_PRIVATE)
        val movieDetailsJson = sharedPreferences.getString("movie_popular", null)

        return if (movieDetailsJson != null) {
            Json.decodeFromString<PopularMoviesResponseModel>(movieDetailsJson)
        } else {
            null
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getMovieDetailsFromSharedPreferences(context: Context): MovieDetailResponseModel? {
        val sharedPreferences = context.getSharedPreferences("MoviesPref", Context.MODE_PRIVATE)
        val movieDetailsJson = sharedPreferences.getString("movie_details", null)

        return if (movieDetailsJson != null) {
            Json.decodeFromString<MovieDetailResponseModel>(movieDetailsJson)
        } else {
            null
        }
    }
}
