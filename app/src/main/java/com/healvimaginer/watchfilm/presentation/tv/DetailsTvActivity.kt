package com.healvimaginer.watchfilm.presentation.tv

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
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.databinding.ActivityDetailsTvBinding
import com.healvimaginer.watchfilm.domain.viewModelFactory.ViewModelFactoryTv
import com.healvimaginer.watchfilm.domain.vo.Status
import com.healvimaginer.watchfilm.presentation.favorite.FavoriteActivity

class DetailsTvActivity : AppCompatActivity() {
    private lateinit var viewmodel: DetailTvViewModel
    private lateinit var binding: ActivityDetailsTvBinding
    companion object {
        const val  EXTRA_TV = "extra_tv"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactoryTv.getInstance(this)
        viewmodel = ViewModelProvider(this,factory)[DetailTvViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvId = extras.getString(EXTRA_TV)
            if (tvId != null) {

                viewmodel.setSelectedTv(tvId)
                viewmodel.getTv().observe(this, {tv ->
                    if (tv != null ) {
                        when (tv.status) {
                            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.progressBar.visibility = View.GONE
                                tv.data?.let { detailview(it) }
                            }
                        }
                    }

                })
            }
        }
    }

    private fun detailview(tvEntity: TvEntity) {
        with(binding) {
            titleTv.text = tvEntity.title
            rilisTv.text = tvEntity.rilis
            kreatorTv.text = tvEntity.kreator
            statusTv.text = tvEntity.status
            networkTv.text = tvEntity.network
            deskriptionTv.text = tvEntity.description


        }
        Glide.with(this)
            .load(tvEntity.image)
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

    private fun setStatusFavorite(statusFavorite: Boolean, tvEntity: TvEntity) {
        if (statusFavorite) {
            binding.addFavorite.setImageResource(R.drawable.ic_favorite_white)
            binding.addFavorite.setOnClickListener {
                val favorite = FavoriteTvEntity(
                    contentId = tvEntity.contentId,
                    description = tvEntity.description,
                    image = tvEntity.image,
                    kreator = tvEntity.kreator,
                    network = tvEntity.network,
                    rilis = tvEntity.rilis,
                    status = tvEntity.status,
                    title = tvEntity.title
                )
                viewmodel.delete(favorite)
            }
        } else {
            binding.addFavorite.setImageResource(R.drawable.ic_not_favorite_white)
            binding.addFavorite.setOnClickListener {
                val favorite = FavoriteTvEntity(
                    contentId = tvEntity.contentId,
                    description = tvEntity.description,
                    image = tvEntity.image,
                    kreator = tvEntity.kreator,
                    network = tvEntity.network,
                    rilis = tvEntity.rilis,
                    status = tvEntity.status,
                    title = tvEntity.title
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