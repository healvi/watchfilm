package com.healvimaginer.watchfilm.presentation.film

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.healvimaginer.watchfilm.R
import com.healvimaginer.watchfilm.domain.viewModelFactory.ViewModelFactory
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.databinding.ActivityDetailsFilmBinding
import com.healvimaginer.watchfilm.domain.vo.Status

class DetailsFilmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsFilmBinding
    companion object {
        const val  EXTRA_FILM = "extra_film"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory.getInstance(this)
        val viewmodel = ViewModelProvider(this,factory)[DetailFilmViewModel::class.java]

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

    private fun populate(filmEntity: FilmsEntity) {
        with(binding) {
            titleFilm.text = filmEntity.title
            rilisFilm.text = filmEntity.rilis
            directorFilm.text = filmEntity.director
            anggaranFilm.text = filmEntity.anggaran
            pendapatanFilm.text = filmEntity.pendapatan
            deskriptionFilm.text = filmEntity.description

        }
        Glide.with(this)
            .load(filmEntity.image)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(binding.imagefilm)
    }
}