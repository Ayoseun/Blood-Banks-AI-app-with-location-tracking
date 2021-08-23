package com.magneto.blood.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magneto.blood.R
import com.magneto.blood.adapter.BeekAdapter
import com.magneto.blood.adapter.ListAdapter
import com.magneto.blood.model.Listmodel
import com.magneto.blood.model.cList
import com.magneto.blood.model.cList2

class BeekFragment : Fragment() {

    //cast model to array
    val custom = ArrayList<cList>()
    val custom3 = ArrayList<cList2>()
    val custom2 = ArrayList<Listmodel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        run {
            val window = requireActivity().window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        }

        //view inflater
        val view = inflater.inflate(R.layout.fragment_beek, container, false)
        //viewBinder
        val rv = view!!.findViewById<RecyclerView>(R.id.beekRv)
        val list = view.findViewById<ListView>(R.id.listV)
        val grps = arrayOf("A+", "0+", "A-", "B+", "AB+", "0-")
        val arr = arrayOf(50, 100, 25, 15, 16, 70, 35, 76)
        (arr.indices).forEach {
            it

            //set each value to the model class and attach them to the custom arrayList
            val each = arr[it]
            custom3.add(cList2(each.toFloat()))
        }
        //attach each value to arrayList
        (grps.indices).forEach { it ->
            val grp = grps[it]

            custom.add(
                cList(grp)
            )
        }
        //attach data to the  adapter
        val listed = BeekAdapter (requireActivity(), custom, custom3)
        //declare adapter
        rv.adapter = listed
        rv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)




        custom2.add(Listmodel("August 1 2021", "Blood Group O+", "5"))
        custom2.add(Listmodel("August 12 2021", "Blood Group A+", "3"))
        custom2.add(Listmodel("August 15 2021", "Blood Group O-", "1"))
        custom2.add(Listmodel("August 15 2021", "Blood Group O+", "8"))
        custom2.add(Listmodel("August 15 2021", "Blood Group AB+", "24"))
        custom2.add(Listmodel("August 15 2021", "Blood Group O+", "7"))

        val listadpt = ListAdapter(requireActivity(), custom2)
        list.adapter = listadpt


// Inflate the layout for this fragment
        return view
    }


}