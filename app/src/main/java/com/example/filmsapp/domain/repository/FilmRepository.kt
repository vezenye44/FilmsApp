package com.example.filmsapp.domain.repository

import com.example.filmsapp.domain.entity.FilmDescription
import com.example.filmsapp.domain.entity.FilmPage

interface FilmRepository {

    suspend fun getFilmDescription(filmId: Int): FilmDescription

    suspend fun getFilmPage(pageNumber: Int): FilmPage
}