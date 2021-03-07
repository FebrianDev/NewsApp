package com.febrian.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.febrian.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.technology.setOnClickListener(this)
        binding.movie.setOnClickListener(this)
        binding.sport.setOnClickListener(this)
        binding.game.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.technology -> {
                switchToList(Constant.TECHNOLOGY)
            }

            R.id.movie -> {
                switchToList(Constant.MOVIE)
            }

            R.id.sport -> {
                switchToList(Constant.SPORT)
            }

            R.id.game -> {
                switchToList(Constant.GAME)
            }
        }

    }

    private fun switchToList(category : String){
        val intent = Intent(applicationContext, ListActivity::class.java)
        intent.putExtra(Constant.CATEGORY, category)
        startActivity(intent)
    }
}