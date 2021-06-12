package com.healvimaginer.watchfilm.presentation.detail.tv

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
import com.healvimaginer.watchfilm.core.data.vo.Resource
import com.healvimaginer.watchfilm.core.domain.model.Tv
import com.healvimaginer.watchfilm.databinding.ActivityDetailsTvBinding
import com.healvimaginer.watchfilm.presentation.favorite.FavoriteActivity
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsTvActivity : AppCompatActivity() {
    private val viewmodel: DetailTvViewModel by viewModel()
    private lateinit var binding: ActivityDetailsTvBinding
    companion object {
        const val  EXTRA_TV = "extra_tv"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val tvId = extras.getString(EXTRA_TV)
            if (tvId != null) {

                viewmodel.setSelectedTv(tvId)
                viewmodel.getTv().observe(this, {tv ->
                    if (tv != null ) {
                        when (tv) {
                            is Resource.loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.success -> {
                                binding.progressBar.visibility = View.GONE
                                tv.data?.let { detailview(it) }
                            }
                        }
                    }

                })
            }
        }
    }

    private fun detailview(tvEntity: Tv) {
        with(binding) {
            titleTv.text = tvEntity.name
            rilisTv.text = tvEntity.first_air_date
            kreatorTv.text = tvEntity.overview
            statusTv.text = tvEntity.name
            networkTv.text = tvEntity.popularity.toString()
            deskriptionTv.text = tvEntity.vote_average.toString()


        }
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/"+tvEntity.poster_path)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(binding.imagetv)
        viewmodel.findTv(tvEntity.contentId).observe(this, {
                film ->
            if (film != null) {
                Log.d("favorite", film.contentId)
                setStatusFavorite(true, tvEntity)
            } else {
                Log.d("Unfavorite", tvEntity.contentId)
                setStatusFavorite(false, tvEntity)
            }
        })

    }

    private fun setStatusFavorite(statusFavorite: Boolean, data: Tv) {
        if (statusFavorite) {
            binding.addFavorite.setImageResource(R.drawable.ic_favorite_white)
            binding.addFavorite.setOnClickListener {
                val favorite = Tv(
                    contentId=data.contentId,
                    name=data.name,
                    overview=data.overview,
                    popularity=data.popularity,
                    poster_path=data.poster_path,
                    backdrop_path=data.backdrop_path,
                    vote_average=data.vote_average,
                    first_air_date=data.first_air_date
                )
                viewmodel.delete(favorite)
            }
        } else {
            binding.addFavorite.setImageResource(R.drawable.ic_not_favorite_white)
            binding.addFavorite.setOnClickListener {
                val favorite = Tv(
                    contentId=data.contentId,
                    name=data.name,
                    overview=data.overview,
                    popularity=data.popularity,
                    poster_path=data.poster_path,
                    backdrop_path=data.backdrop_path,
                    vote_average=data.vote_average,
                    first_air_date=data.first_air_date
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