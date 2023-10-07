package com.example.filmsapp.presentation.films_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.filmsapp.R
import com.example.filmsapp.data.retrofit.ApiFilms
import com.example.filmsapp.databinding.FilmsListItemBinding
import com.example.filmsapp.domain.entity.Film

class FilmItemViewHolder(
    view: View,
    private val clickListener: (Int) -> Unit,
) : ViewHolder(view) {

    private val binding = FilmsListItemBinding.bind(itemView)

    fun bind(film: Film) {
        binding.titleTextView.text = film.title
        binding.root.setOnClickListener {
            clickListener.invoke(film.id)
        }
        binding.posterImageView.load("${ApiFilms.IMAGE_URL}${ApiFilms.IMAGE_END_POINT}${film.posterPath}") {
            this.placeholder(R.drawable.ic_base_image)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickListener: (Int) -> Unit): FilmItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FilmsListItemBinding.inflate(layoutInflater, parent, false)

            return FilmItemViewHolder(binding.root, clickListener)
        }
    }
}