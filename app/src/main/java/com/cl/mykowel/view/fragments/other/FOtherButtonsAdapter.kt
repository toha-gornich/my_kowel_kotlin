package com.cl.mykowel.view.fragments.other

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cl.mykowel.R

class FOtherButtonsAdapter(
    myContext: Context,
    private val list: ArrayList<FOtherButtonModel>
) : ArrayAdapter<FOtherButtonModel>(myContext, R.layout.other_buttons,list) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.other_buttons, null)

        val imageView: ImageView = view.findViewById(R.id.imageViewOtherButtons)
        val title: TextView = view.findViewById(R.id.titleFragmentOtherTV)
        val description: TextView = view.findViewById(R.id.descriptionFragmentOtherTV)

        imageView.setImageResource(list[position].resource)
        title.text = list[position].title
        description.text = list[position].description


        return view
    }
}