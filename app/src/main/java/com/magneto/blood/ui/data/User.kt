package com.magneto.blood.ui.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.magneto.blood.R
import com.magneto.blood.databinding.ActivityOnboardBinding
import com.magneto.blood.databinding.ActivityUserBinding

class User : AppCompatActivity() {
    var state = 0

    //instance of loginFragment and signupFragment
    val login = loginFragment()
    val signUp = SignupFragment()


    private lateinit var binding : ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserBinding.inflate(layoutInflater)
       val view = binding.root
        setContentView(view)

        loaded(login)


        binding.userClick.setOnClickListener {
            when (state) {
                0 -> {
                    state = 1
                    binding.textView3.text ="Create an Account"
                    binding.userClick.text = "SIGN UP"
                    loaded(login)
                }
                1 -> {
                    state = 0
                    binding.userClick.text = "LOG IN"
                    binding.textView3.text="Already have an Account"
                    loaded(signUp)
                }
            }
        }
    }

    fun loaded(fragment: androidx.fragment.app.Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.user, fragment)
        fragmentTransaction.commit()
    }
}
