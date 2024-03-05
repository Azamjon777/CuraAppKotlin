package com.azamjon.cursapikotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomArrayAdapter(
    context: Context,
    resource: Int,
    listItem: List<ListItemClass>,
    private val inflater: LayoutInflater
) :
    ArrayAdapter<ListItemClass?>(context, resource, listItem) {
    private var listItem: List<ListItemClass> = ArrayList()

    init {
        this.listItem = listItem
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val viewHolder: ViewHolder
        val listItemMain = listItem[position]
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_1, null, false)
            viewHolder = ViewHolder()
            viewHolder.data1 = convertView.findViewById(R.id.tvData1)
            viewHolder.data2 = convertView.findViewById(R.id.tvData2)
            viewHolder.data3 = convertView.findViewById(R.id.tvData3)
            viewHolder.data4 = convertView.findViewById(R.id.tvData4)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.data1?.text = listItemMain.data1
        viewHolder.data2?.text = listItemMain.data2
        viewHolder.data3?.text = listItemMain.data3
        viewHolder.data4?.text = listItemMain.data4
        return convertView!!
    }

    private class ViewHolder {
        var data1: TextView? = null
        var data2: TextView? = null
        var data3: TextView? = null
        var data4: TextView? = null
    }
}