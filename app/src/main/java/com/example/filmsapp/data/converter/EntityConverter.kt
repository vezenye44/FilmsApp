package com.example.filmsapp.data.converter

import com.example.filmsapp.data.entity.description_film.DescriptionFilm
import com.example.filmsapp.data.entity.films.FilmDto
import com.example.filmsapp.data.entity.films.FilmPageDto
import com.example.filmsapp.domain.entity.Film
import com.example.filmsapp.domain.entity.FilmDescription
import com.example.filmsapp.domain.entity.FilmPage

object EntityConverter {

    fun FilmDto.toDomainEntity(): Film {
        return Film(
            id = id,
            title = title,
            posterPath = posterPath,
        )
    }

    fun DescriptionFilm.toDomainEntity(): FilmDescription {
        return FilmDescription(
            title = title,
            overview = overview,
            posterPath = backdropPath
        )
    }

    fun FilmPageDto.toDomainEntity(): FilmPage {
        return FilmPage(
            page = page,
            results = results?.map { it.toDomainEntity() }
                ?: throw IllegalStateException(" А список то пустой "),
            totalPages = totalPages,
            totalResults = totalResults
        )
    }
}
