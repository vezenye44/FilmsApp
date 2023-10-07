package com.example.filmsapp.presentation.films_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsapp.domain.entity.Film
import com.example.filmsapp.domain.entity.FilmPage
import com.example.filmsapp.domain.repository.FilmRepository
import kotlinx.coroutines.launch

class FilmListViewModel(
    private val filmRepository: FilmRepository,
) : ViewModel() {

    private var currentFilmPage: Int = 1

    private val listFilms = mutableListOf<Film>()

    private val filmsLiveData = MutableLiveData<List<Film>>()
    private val isLastPageLiveData = MutableLiveData<Boolean>()

    fun getFilmsLiveData(): LiveData<List<Film>> {
        return filmsLiveData
    }

    fun getIsLastPageLiveData(): LiveData<Boolean> {
        return isLastPageLiveData
    }

    private fun getFilmPage(page: Int) {
        viewModelScope.launch {
            val newFilmPage = filmRepository.getFilmPage(page)

            currentFilmPage = page + 1
            updateFilmsLiveData(newFilmPage)
            updateIsLastPageLiveData(newFilmPage)
        }
    }

    fun getNextFilmPage() {
        getFilmPage(currentFilmPage)
    }

    private fun updateIsLastPageLiveData(newFilmPage: FilmPage) {
        isLastPageLiveData.value = newFilmPage.totalPages <= newFilmPage.page
    }

    private fun updateFilmsLiveData(newFilmPage: FilmPage) {
        listFilms.addAll(newFilmPage.results)
        filmsLiveData.value = listFilms
    }
}