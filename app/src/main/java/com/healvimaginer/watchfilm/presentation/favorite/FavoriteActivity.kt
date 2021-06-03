package com.healvimaginer.watchfilm.presentation.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.healvimaginer.watchfilm.R
import com.healvimaginer.watchfilm.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionbar = supportActionBar
        actionbar?.title = "Favorite"
        val sectionPagerAdapter = FavoriteSectionAdapter(this,supportFragmentManager)
        binding.viewPagerFavorite.adapter = sectionPagerAdapter
        binding.tabsFavorite.setupWithViewPager(binding.viewPagerFavorite)
        supportActionBar?.elevation = 0f
    }
}