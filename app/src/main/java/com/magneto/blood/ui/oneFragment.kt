package com.magneto.blood.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magneto.blood.R
import com.magneto.blood.viewmodel.OneViewModel

class oneFragment : Fragment() {

    companion object {
        fun newInstance() = oneFragment()
    }

    private lateinit var viewModel: OneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.one_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}