package com.magneto.blood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magneto.blood.R
import com.magneto.blood.model.cList
import com.magneto.blood.model.cList2
import com.skydoves.progressview.progressView
import kotlinx.android.synthetic.main.slate.view.*

class BeekAdapter(
    val context: Context,
    private val Act: ArrayList<cList>,
    private val Act2: ArrayList<cList2>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.slate, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return Act.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val guess = Act[position]
        val guess2 = Act2[position]

        holder.itemView.num.text = guess.text

        holder.itemView.progressView.apply {
            progress = guess2.progressBar
            progressView(context) {
                setSize(300, 35)
                setProgress(guess2.progressBar)
                setMax(100f)
                setRadius(12f)
                setDuration(1200L)
                setAutoAnimate(true)

            }
        }

    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!),
        View.OnClickListener {
        init {
            itemView?.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }

    }

}