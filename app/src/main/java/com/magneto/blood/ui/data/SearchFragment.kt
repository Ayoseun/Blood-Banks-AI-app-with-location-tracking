package com.magneto.blood.ui.data

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.magneto.blood.R
import com.magneto.blood.adapter.RecommenderAdapter
import com.magneto.blood.model.DogsApi
import org.json.JSONObject


class SearchFragment : Fragment() {
    private val imageList = ArrayList<DogsApi>()
    private lateinit var dogsRv: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        dogsRv = view.findViewById<RecyclerView>(R.id.dogsRv)
        val searchbtn = view.findViewById<FloatingActionButton>(R.id.searchbtn)
        val dogNameText = view.findViewById<EditText>(R.id.dogNameText)

        searchbtn.setOnClickListener {
            var name = dogNameText.text.toString()

            searchdogs(name)
        }
        dogsRv.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL)

        return view
    }

    private fun searchdogs(name: String) {

        imageList.clear()
        AndroidNetworking.get("https://api-ris.herokuapp.com/recommend/agege?API_key=mag-cloba")
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String) {
                    val result = JSONObject(response)
                    val image = result.getJSONObject("BLOOD_BANK")
                    for (i in 0 until image.length()){
                        val one = image.getString(i.toString())
                        imageList.add(
                            DogsApi(one))
                    }





                    dogsRv.adapter = RecommenderAdapter(requireContext(), imageList)
                }

                override fun onError(anError: ANError?) {

                }

            })
    }

}