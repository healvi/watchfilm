package com.healvimaginer.watchfilm.presentation.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.healvimaginer.watchfilm.R
import com.healvimaginer.watchfilm.databinding.ActivityMainBinding
import com.healvimaginer.watchfilm.presentation.favorite.FavoriteActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this,supportFragmentManager)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        supportActionBar?.elevation = 0f
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