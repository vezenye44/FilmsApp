package com.example.filmsapp.presentation.film_description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.filmsapp.data.retrofit.ApiFilms
import com.example.filmsapp.databinding.FragmentFilmDescriptionBinding
import com.example.filmsapp.domain.entity.FilmDescription
import org.koin.androidx.viewmodel.ext.android.viewModel

class DescriptionFilmFragment : Fragment() {

    private var _binding: FragmentFilmDescriptionBinding? = null
    private val binding get() = _binding!!

    private val filmDescriptionViewModel: DescriptionFilmViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilmDescriptionBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filmDescriptionViewModel.apply {
            getDescriptionFilmLiveData().observe(viewLifecycleOwner) {
                renderData(it)
            }

            arguments?.let {
                setFilmId(it.getInt(KEY_FILM_ID))
            }
        }
    }

    private fun renderData(filmDescription: FilmDescription) {
        binding.titleTextView.text = filmDescription.title
        binding.descriptionTextView.text = filmDescription.overview
        binding.posterImageView.load("${ApiFilms.IMAGE_URL}${ApiFilms.IMAGE_END_POINT}${filmDescription.posterPath}")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val KEY_FILM_ID = "KEY_FILM_ID"

        fun newInstance(filmId: Int): DescriptionFilmFragment {
            val bundle = Bundle().apply {
                putInt(KEY_FILM_ID, filmId)
            }

            return DescriptionFilmFragment().apply {
                arguments = bundle
            }
        }
    }
}