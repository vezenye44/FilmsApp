package com.example.filmsapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmsapp.R
import com.example.filmsapp.presentation.films_list.FilmListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FilmListFragment.newInstance(), "")
                .commit()
        }
    }
}