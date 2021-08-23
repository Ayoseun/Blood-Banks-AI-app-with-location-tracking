package com.magneto.blood.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.magneto.blood.R
import com.magneto.blood.databinding.ActivityMainBinding
import com.magneto.blood.viewmodel.MainActivityViewModel
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val handler = Handler()
        handler.postDelayed({
//this function takes the numbers from shared prefrences and saves them
            startActivity(Intent(this, Onboard::class.java))
            finish()
           //end
        }, 1500)
        //viewModel binding
        val viewModel: MainActivityViewModel =
            ViewModelProvider(this).get(MainActivityViewModel::class.java)
        run {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
            window.statusBarColor = Color.parseColor("#12526474")
        }

    }
}