package com.example.filmsapp.di

import com.example.filmsapp.data.data_source.RemoteDataSource
import com.example.filmsapp.data.data_source.RemoteDataSourceImpl
import com.example.filmsapp.data.repository.FilmRepositoryImpl
import com.example.filmsapp.data.retrofit.ApiFilms
import com.example.filmsapp.domain.repository.FilmRepository
import com.example.filmsapp.presentation.film_description.DescriptionFilmViewModel
import com.example.filmsapp.presentation.films_list.FilmListViewModel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KoinModules {

    val repositoryModule = module {
        single<FilmRepository> {
            FilmRepositoryImpl(
                remoteDataSource = get()
            )
        }
    }

    val dataSourceModule = module {
        single<RemoteDataSource> {
            RemoteDataSourceImpl(
                apiFilms = get()
            )
        }
    }

    val viewModulesModule = module {
        viewModel {
            FilmListViewModel(filmRepository = get())
        }

        viewModel {
            DescriptionFilmViewModel(filmRepository = get())
        }
    }

    val remoteModule = module {
        single {
            Retrofit.Builder()
                .baseUrl(ApiFilms.BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                )
                .build()
                .create(ApiFilms::class.java)
        }
    }
}