package com.febrian.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val delay : Long = 1000

        Handler().postDelayed({
                 startActivity(Intent(applicationContext, MainActivity::class.java))
        }, delay )
    }
}