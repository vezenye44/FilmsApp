package com.example.filmsapp.presentation.film_description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsapp.domain.entity.FilmDescription
import com.example.filmsapp.domain.repository.FilmRepository
import kotlinx.coroutines.launch

class DescriptionFilmViewModel(
    private val filmRepository: FilmRepository,
) : ViewModel() {

    fun setFilmId(filmId: Int) {
        viewModelScope.launch {
            descriptionFilmLiveData.value = filmRepository.getFilmDescription(filmId)
        }
    }

    private val descriptionFilmLiveData = MutableLiveData<FilmDescription>()

    fun getDescriptionFilmLiveData() = descriptionFilmLiveData as LiveData<FilmDescription>
}