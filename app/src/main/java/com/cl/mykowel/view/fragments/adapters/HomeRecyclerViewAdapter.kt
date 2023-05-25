package com.cl.mykowel.view.fragments.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cl.mykowel.databinding.FragmentHomeBinding
import com.cl.mykowel.models.model_news.ItemNews


class HomeRecyclerViewAdapter(
    private val news: ArrayList<ItemNews>, private val context: Context?
) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = news.get(position)

        holder.titleView.text = item.title
        //        holder.contentTextView.setText(news.get(position).getContentText());

        val imagePath = "" +
                news.get(position).image
        if (context != null) {
            Glide.with(context)
                .load(imagePath)
                .into(holder.imageView)
        }
//        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = news.size

    inner class ViewHolder(binding: FragmentHomeBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }
        val titleView: TextView = binding.fragmentNewsTitle
        val imageView: ImageView = binding.fragmentNewsImage
        override fun onClick(v: View?) {
            var position = bindingAdapterPosition
            var itemNews: ItemNews = news.get(position)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(itemNews.url))
            context?.startActivity(intent)
        }

//        override fun toString(): String {
//            return super.toString() + " '" + contentView.text + "'"
//        }
    }

}