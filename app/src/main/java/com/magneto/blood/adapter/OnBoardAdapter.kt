package com.magneto.blood.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.magneto.blood.ui.oneFragment
import com.magneto.blood.ui.twoFragment

class OnboardAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->{
                oneFragment()
            }
            1->{
                twoFragment()
            }

            else -> oneFragment()
        }

    }

    override fun getCount(): Int {
        return 2
    }


}