package com.healvimaginer.watchfilm.presentation.detail.film

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.healvimaginer.watchfilm.R
import com.healvimaginer.watchfilm.data.vo.Resource
import com.healvimaginer.watchfilm.databinding.ActivityDetailsFilmBinding
import com.healvimaginer.watchfilm.domain.model.Film
import com.healvimaginer.watchfilm.presentation.favorite.FavoriteActivity
import org.koin.android.viewmodel.ext.android.viewModel


class DetailsFilmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsFilmBinding
    private val viewmodel: DetailFilmViewModel by viewModel()
    companion object {
        const val  EXTRA_FILM = "extra_film"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val filmId = extras.getString(EXTRA_FILM)
            if (filmId != null) {
                viewmodel.setSelectedFilm(filmId)
                viewmodel.getFilm().observe(this, {film ->
                    if (film != null ) {
                        when (film) {
                            is Resource.loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.success -> {
                                binding.progressBar.visibility = View.GONE
                                film.data?.let { populate(it) }
                            }
                        }
                    }

                })
            }
        }
    }

    private fun populate(filmEntity: Film) {
        with(binding) {
            titleFilm.text = filmEntity.title
            rilisFilm.text = filmEntity.release_date
            directorFilm.text = filmEntity.popularity.toString()
            anggaranFilm.text = filmEntity.popularity.toString()
            pendapatanFilm.text = filmEntity.vote_average.toString()
            deskriptionFilm.text = filmEntity.overview

        }
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/"+filmEntity.poster_path)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(binding.imagefilm)

        viewmodel.findFilm(filmEntity.contentId).observe(this, {
                film ->
            if (film != null) {
                Log.d("favorite", film.contentId)
                setStatusFavorite(true, filmEntity)
            } else {
                Log.d("Unfavorite", filmEntity.contentId)
                setStatusFavorite(false, filmEntity)
            }
        })
    }

    private fun setStatusFavorite(statusFavorite: Boolean, it: Film) {
        if (statusFavorite) {
            binding.addFavorite.setImageResource(R.drawable.ic_favorite_white)
            binding.addFavorite.setOnClickListener { data ->
                val favorite = Film(
                    contentId=it.contentId,
                    title=it.title,
                    overview=it.overview,
                    popularity=it.popularity,
                    poster_path=it.poster_path,
                    backdrop_path=it.backdrop_path,
                    vote_average=it.vote_average,
                    release_date=it.release_date,
                )
                viewmodel.delete(favorite)
            }
        } else {
            binding.addFavorite.setImageResource(R.drawable.ic_not_favorite_white)
            binding.addFavorite.setOnClickListener { data ->
                val favorite = Film(
                    contentId=it.contentId,
                    title=it.title,
                    overview=it.overview,
                    popularity=it.popularity,
                    poster_path=it.poster_path,
                    backdrop_path=it.backdrop_path,
                    vote_average=it.vote_average,
                    release_date=it.release_date,
                )
                viewmodel.insert(favorite)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                true
            }
            else -> false
        }
    }
}