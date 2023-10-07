package com.example.filmsapp.domain.entity

data class FilmPage(
    val page: Int,
    val results: List<Film>,
    val totalPages: Int,
    val totalResults: Int,
)
