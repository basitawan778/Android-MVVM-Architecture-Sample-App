package com.example.baseapplication.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baseapplication.R
import com.example.baseapplication.databinding.ActivityMainBinding
import com.example.baseapplication.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.quotesLL.setOnClickListener {
//            startActivity(Intent(this, QuotesActivity::class.java))
        }

        binding.exerciseCV.setOnClickListener {
//            startActivity(Intent(this, QuotesActivity::class.java))
        }
    }
}