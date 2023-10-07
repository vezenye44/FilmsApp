package com.example.filmsapp.data.data_source

import com.example.filmsapp.data.converter.EntityConverter.toDomainEntity
import com.example.filmsapp.data.retrofit.ApiFilms
import com.example.filmsapp.domain.entity.FilmDescription
import com.example.filmsapp.domain.entity.FilmPage

class RemoteDataSourceImpl(
    private val apiFilms: ApiFilms,
) : RemoteDataSource {

    override suspend fun getFilms(page: Int): FilmPage =
        apiFilms.loadFilms(page = page).toDomainEntity()

    override suspend fun getDescriptionFilm(idFilm: Int): FilmDescription =
        apiFilms.loadDescriptionFilm(idFilm).toDomainEntity()
}