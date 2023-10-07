package com.example.filmsapp.presentation.films_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmsapp.R
import com.example.filmsapp.databinding.FragmentFilmListBinding
import com.example.filmsapp.presentation.film_description.DescriptionFilmFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmListFragment : Fragment() {

    private var _binding: FragmentFilmListBinding? = null
    private val binding get() = _binding!!

    private lateinit var clickListener: (Int) -> Unit
    private val filmsViewModel: FilmListViewModel by viewModel()
    private var isLastPage = false
    private lateinit var filmListAdapter: FilmListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilmListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        viewModelsObserves()
    }

    private fun viewModelsObserves() {
        filmsViewModel.getIsLastPageLiveData().observe(viewLifecycleOwner) { isLastPage = it }
        filmsViewModel.getFilmsLiveData().observe(viewLifecycleOwner, filmListAdapter::setFilmsList)
    }

    private fun initRecyclerView() {
        binding.filmsListRecyclerView.apply {
            val layoutManager = LinearLayoutManager(requireContext())

            clickListener = {
                requireActivity().supportFragmentManager.beginTransaction()
                    .hide(this@FilmListFragment)
                    .add(R.id.container, DescriptionFilmFragment.newInstance(it), "")
                    .addToBackStack("")
                    .commit()
            }
            filmListAdapter = FilmListAdapter(clickListener)

            this.layoutManager = layoutManager
            this.adapter = filmListAdapter
            this.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
                override fun isLastPage(): Boolean = isLastPage

                override fun isLoading(): Boolean = false

                override fun loadMoreItems() {
                    filmsViewModel.getNextFilmPage()
                }
            })

            filmsViewModel.getNextFilmPage()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): FilmListFragment {
            return FilmListFragment()
        }
    }
}