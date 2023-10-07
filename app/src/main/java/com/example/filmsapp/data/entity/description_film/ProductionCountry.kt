package com.example.filmsapp.data.entity.description_film

import com.google.gson.annotations.SerializedName

data class ProductionCountry(
    @field:SerializedName("iso_3166_1") val Iso: String,
    val name: String,
)