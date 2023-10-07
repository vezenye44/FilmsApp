package com.example.filmsapp.presentation.films_list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.domain.entity.Film

class FilmListAdapter(
    private val clickListener: (Int) -> Unit,
) : RecyclerView.Adapter<FilmItemViewHolder>() {

    private var list = listOf<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemViewHolder {
        return FilmItemViewHolder.create(parent, clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FilmItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setFilmsList(filmsList: List<Film>) {
        val oldSize = list.size
        list = filmsList
        notifyItemRangeChanged(oldSize, list.size)
    }
}