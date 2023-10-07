package com.example.filmsapp.data.entity.films

import com.google.gson.annotations.SerializedName

data class FilmPageDto(
    val page: Int,
    val results: List<FilmDto>?,
    @field:SerializedName("total_pages") val totalPages: Int,
    @field:SerializedName("total_results") val totalResults: Int,
)