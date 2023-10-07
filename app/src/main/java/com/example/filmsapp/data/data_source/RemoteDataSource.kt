package com.example.filmsapp.data.data_source

import com.example.filmsapp.domain.entity.FilmDescription
import com.example.filmsapp.domain.entity.FilmPage

interface RemoteDataSource {

    suspend fun getFilms(page: Int): FilmPage

    suspend fun getDescriptionFilm(idFilm: Int): FilmDescription
}