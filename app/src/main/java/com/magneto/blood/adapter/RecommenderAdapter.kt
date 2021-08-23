package com.magneto.blood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magneto.blood.R
import com.magneto.blood.model.DogsApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dogs_rv_layout.view.*

class RecommenderAdapter (val context: Context?, private val dogsImages : ArrayList<DogsApi>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.dogs_rv_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dogsImages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val get = dogsImages[position]
           holder.itemView.dogImage.text=get.message
    }

    class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!), View.OnClickListener {
        override fun onClick(v: View?) {

        }

        init {
            itemView.setOnClickListener(this)
        }

        val dogImage = itemView.dogImage!!
    }

}