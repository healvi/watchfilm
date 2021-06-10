package com.healvimaginer.watchfilm.presentation.film

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.healvimaginer.watchfilm.R
import com.healvimaginer.watchfilm.domain.viewModelFactory.ViewModelFactory
import com.healvimaginer.watchfilm.databinding.ActivityDetailsFilmBinding
import com.healvimaginer.watchfilm.domain.model.Film
import com.healvimaginer.watchfilm.domain.vo.Status
import com.healvimaginer.watchfilm.presentation.favorite.FavoriteActivity

class DetailsFilmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsFilmBinding
    private lateinit var viewmodel: DetailFilmViewModel
    companion object {
        const val  EXTRA_FILM = "extra_film"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory.getInstance(this)
        viewmodel = ViewModelProvider(this,factory)[DetailFilmViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val filmId = extras.getString(EXTRA_FILM)
            if (filmId != null) {
                viewmodel.setSelectedFilm(filmId)
                viewmodel.getFilm().observe(this, {film ->
                    if (film != null ) {
                        when (film.status) {
                            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
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
            directorFilm.text = filmEntity.overview
            anggaranFilm.text = filmEntity.popularity.toString()
            pendapatanFilm.text = filmEntity.vote_average.toString()
            deskriptionFilm.text = filmEntity.first_air_date

        }
        Glide.with(this)
            .load(filmEntity.poster_path)
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
                    name=it.name,
                    overview=it.overview,
                    popularity=it.popularity,
                    poster_path=it.poster_path,
                    backdrop_path=it.backdrop_path,
                    vote_average=it.vote_average,
                    release_date=it.release_date,
                    first_air_date=it.first_air_date
                )
                viewmodel.delete(favorite)
            }
        } else {
            binding.addFavorite.setImageResource(R.drawable.ic_not_favorite_white)
            binding.addFavorite.setOnClickListener { data ->
                val favorite = Film(
                    contentId=it.contentId,
                    title=it.title,
                    name=it.name,
                    overview=it.overview,
                    popularity=it.popularity,
                    poster_path=it.poster_path,
                    backdrop_path=it.backdrop_path,
                    vote_average=it.vote_average,
                    release_date=it.release_date,
                    first_air_date=it.first_air_date
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