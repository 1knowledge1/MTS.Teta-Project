package ru.knowledge.mtstetaproject.movies.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.knowledge.mtstetaproject.BuildConfig
import ru.knowledge.mtstetaproject.movies.network.dto.MovieResponse
import ru.knowledge.mtstetaproject.movies.network.dto.PopularMoviesResponse

const val BASE_URL = "https://api.themoviedb.org/3/"

interface TmdbApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") pageNumber: Int = 1,
        @Query("language") language: String = "ru-RU",
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): PopularMoviesResponse?

    @GET("movie/{id}")
    suspend fun getMovieById(
        @Path("id") movieId: Long,
        @Query("append_to_response") appendToResponse: String = "credits,release_dates",
        @Query("language") language: String = "ru-RU",
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MovieResponse?

    companion object {
        fun create(): TmdbApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TmdbApiService::class.java)
        }
    }
}