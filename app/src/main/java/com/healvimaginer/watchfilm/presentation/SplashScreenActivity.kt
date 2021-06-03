package com.healvimaginer.watchfilm.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.healvimaginer.watchfilm.databinding.ActivitySplashScreenBinding
import com.healvimaginer.watchfilm.presentation.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val toMAin = Intent(this@SplashScreenActivity, HomeActivity::class.java)
            startActivity(toMAin)
        }, 3000)
    }
}