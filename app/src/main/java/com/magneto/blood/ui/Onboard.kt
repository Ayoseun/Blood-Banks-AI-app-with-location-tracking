package com.magneto.blood.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.magneto.blood.R
import com.magneto.blood.adapter.OnboardAdapter
import com.magneto.blood.databinding.ActivityOnboardBinding
import com.magneto.blood.ui.data.User
import com.magneto.blood.viewmodel.MainActivityViewModel
import com.magneto.blood.viewmodel.OnboardViewModel

class Onboard : AppCompatActivity() {
//initialize Adapter
    private lateinit var onboardAdapter: OnboardAdapter
    //initialize animation
    lateinit var anims: Animation
    //set up viewBinding
    private lateinit var binding: ActivityOnboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //bind views
        binding= ActivityOnboardBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

        //setup viewModel
        val viewModel: OnboardViewModel =
            ViewModelProvider(this).get(OnboardViewModel::class.java)
binding.skip.setOnClickListener {
    startActivity(Intent(this, User::class.java))
    finish()
}
        onboardAdapter =  OnboardAdapter(supportFragmentManager)
binding.pager.apply {
    adapter = onboardAdapter
   offscreenPageLimit = 2
    currentItem = 0
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
            changingTabs(position)
        }

    })
}
    //default tab
    binding.img1.setImageResource(R.drawable.two)
    anims = AnimationUtils.loadAnimation(this,R.anim.stay2)

    }

    //functions
    private fun changingTabs(position: Int) {
        if (position == 0) {
            binding.img1.setImageResource(R.drawable.two)
            binding.img1.startAnimation(anims)
            binding.img2.setImageResource(R.drawable.one)


        }
        if (position == 1) {

            binding.img1.setImageResource(R.drawable.one)
            binding.img2.startAnimation(anims)

            binding.img2.setImageResource(R.drawable.two)


        }
    }
}

