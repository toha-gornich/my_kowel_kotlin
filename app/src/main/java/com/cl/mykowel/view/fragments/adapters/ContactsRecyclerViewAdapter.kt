package com.cl.mykowel.view.fragments.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.cl.mykowel.view.fragments.placeholder.PlaceholderContent.PlaceholderItem
import com.cl.mykowel.databinding.FragmentContactsBinding
import com.cl.mykowel.databinding.FragmentContactsListBinding
import com.cl.mykowel.databinding.FragmentHomeBinding
import com.cl.mykowel.models.model_contact.ItemContact
import com.cl.mykowel.models.model_news.ItemNews



class ContactsRecyclerViewAdapter(
    private val contact: List<ItemContact>, private val context: Context?
) : RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentContactsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = contact.get(position)

        holder.ownerView.text = item.owner
        holder.pnumberView.text = item.pnumber
        //        holder.contentTextView.setText(news.get(position).getContentText());


//        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = contact.size

    inner class ViewHolder(binding: FragmentContactsBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }
        var ownerView: TextView = binding.ownerTxtView
        var pnumberView: TextView = binding.pNumberTxtView
        override fun onClick(v: View?) {
//            var position = bindingAdapterPosition
//            var itemNews: ItemNews = news.get(position)
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(itemNews.url))
//            context?.startActivity(intent)
        }

//        override fun toString(): String {
//            return super.toString() + " '" + contentView.text + "'"
//        }
    }
}