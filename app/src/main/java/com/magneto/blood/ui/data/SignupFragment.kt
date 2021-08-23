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


class SignupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        val sign = view!!.findViewById<Button>(R.id.butSig)

        sign.setOnClickListener {

            startActivity(Intent(requireActivity(), Display::class.java))
            requireActivity().finish()
            val close = User()
            close.finish()
        }
        // Inflate the layout for this fragment

        return view

    }
}