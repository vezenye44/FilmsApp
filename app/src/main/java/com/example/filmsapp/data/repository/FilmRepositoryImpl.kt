package com.example.filmsapp.data.repository

import com.example.filmsapp.data.data_source.RemoteDataSource
import com.example.filmsapp.domain.entity.FilmDescription
import com.example.filmsapp.domain.entity.FilmPage
import com.example.filmsapp.domain.repository.FilmRepository

class FilmRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : FilmRepository {

    override suspend fun getFilmDescription(filmId: Int): FilmDescription {
        return remoteDataSource.getDescriptionFilm(filmId)
    }

    override suspend fun getFilmPage(pageNumber: Int): FilmPage {
        return remoteDataSource.getFilms(pageNumber)
    }
}