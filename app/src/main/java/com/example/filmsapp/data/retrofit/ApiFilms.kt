package com.example.filmsapp.data.retrofit

import com.example.filmsapp.BuildConfig
import com.example.filmsapp.data.entity.description_film.DescriptionFilm
import com.example.filmsapp.data.entity.films.FilmPageDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiFilms {
    @GET(FILMS_END_POINT)
    suspend fun loadFilms(
        @Query(API_KEY) apiKey: String = BuildConfig.apiKey,
        @Query(PAGE) page: Int,
    ): FilmPageDto

    @GET("$DESCRIPTION_FILMS_END_POINT/{idFilm}")
    suspend fun loadDescriptionFilm(
        @Path("idFilm") idFilm: Int,
        @Query(API_KEY) apiKey: String = BuildConfig.apiKey,
    ): DescriptionFilm

    companion object {
        private const val FILMS_END_POINT = "/3/discover/movie"
        private const val DESCRIPTION_FILMS_END_POINT = "/3/movie"
        private const val API_KEY = "api_key"
        private const val PAGE = "page"
        const val BASE_URL = "https://api.themoviedb.org"
        const val IMAGE_URL = "https://image.tmdb.org/"
        const val IMAGE_END_POINT = "/t/p/w500"
    }
}