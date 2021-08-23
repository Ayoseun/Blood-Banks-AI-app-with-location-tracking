package com.magneto.blood.ui.data

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.magneto.blood.R
import com.magneto.blood.ui.Display


class loginFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val login = view!!.findViewById<Button>(R.id.login)

        login.setOnClickListener {

            startActivity(Intent(requireActivity(), Display::class.java))
            requireActivity().finish()
            val close = User()
            close.finish()
        }
        return view


    }


}