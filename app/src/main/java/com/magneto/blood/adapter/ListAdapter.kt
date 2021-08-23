package com.magneto.blood.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.magneto.blood.R
import com.magneto.blood.model.Listmodel

class ListAdapter(
    private val getContext: Context,
    private val customListITem: ArrayList<Listmodel>
) :
    ArrayAdapter<Listmodel>(getContext, 0, customListITem) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listLayout = convertView
        val holder: ViewHolder

        if (listLayout == null) {

            val inflateList = (getContext as Activity).layoutInflater
            listLayout = inflateList.inflate(R.layout.slate2, parent, false)

            holder = ViewHolder()

            holder.mtext = listLayout!!.findViewById(R.id.date)
            holder.mtext2 = listLayout.findViewById(R.id.blood)
            holder.mtext3 = listLayout.findViewById(R.id.valu)
            listLayout.tag = holder
        } else {
            holder = listLayout.tag as ViewHolder
        }
        val listItem = customListITem[position]
        holder.mtext!!.text = listItem.date
        holder.mtext3!!.text = "${listItem.volume}L"
        holder.mtext2!!.text = listItem.blood

        return listLayout
    }

    class ViewHolder {
        internal var mtext: TextView? = null
        internal var mtext2: TextView? = null
        internal var mtext3: TextView? = null
    }
}