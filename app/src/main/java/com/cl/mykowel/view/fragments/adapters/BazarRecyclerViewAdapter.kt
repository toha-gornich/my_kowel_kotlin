package com.cl.mykowel.view.fragments.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cl.mykowel.databinding.FragmentBazarBinding
import com.cl.mykowel.models.ItemBazar


class BazarRecyclerViewAdapter(
    private val list: List<ItemBazar>, private val context: Context?
) : RecyclerView.Adapter<BazarRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentBazarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list.get(position)

        holder.titleTextView.text = item.title
        holder.descriptionTextView.text = item.description
        holder.priceTextView.text = item.price
        holder.phoneTextView.text = item.pnumber
        val imagePath = "" +
                list.get(position).photo
        if (context != null) {
            Glide.with(context)
                .load(imagePath)
                .into(holder.bazarImageView)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(binding: FragmentBazarBinding) : RecyclerView.ViewHolder(binding.root) {

        var titleTextView: TextView = binding.titleBazarText

        var descriptionTextView: TextView = binding.descriptionBazarText

        var priceTextView: TextView = binding.priceBazarText


        var bazarImageView: ImageView = binding.photoBazarImage

        var phoneTextView: TextView = binding.phoneBazarText


//        override fun toString(): String {
//            return super.toString() + " '" + contentView.text + "'"
//        }
    }

}
