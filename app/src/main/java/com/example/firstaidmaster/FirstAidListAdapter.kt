package com.example.firstaidmaster

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.core.os.persistableBundleOf

class FirstAidListAdapter (val context: Context, val DataList: ArrayList<FirstAidName>): BaseAdapter() {
    override fun getCount(): Int {
        return DataList.size
    }

    override fun getItem(position: Int): Any {
        return DataList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val data = DataList[position]

        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_edu_image, null)
        val photo = view.findViewById<ImageView>(R.id.imageView_1)
        photo.setImageResource(data.photo)

        return view
    }
}