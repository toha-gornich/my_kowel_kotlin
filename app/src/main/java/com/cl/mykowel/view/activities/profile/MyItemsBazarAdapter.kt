package com.cl.mykowel.view.activities.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide
import com.cl.mykowel.R
import com.cl.mykowel.databinding.ActivityMyItemBazarBinding
import com.cl.mykowel.models.model_itemBazar.ItemBazar
import com.cl.mykowel.models.services.RetroInstanceMyKowel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyItemsBazarAdapter(private val list: ArrayList<ItemBazar>, private val context: Context?) :
    Adapter<MyItemsBazarAdapter.ViewHolder>() {



    inner class ViewHolder(binding: ActivityMyItemBazarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var titleTextView: TextView = binding.titleBazarText

        var descriptionTextView: TextView = binding.descriptionBazarText

        var priceTextView: TextView = binding.priceBazarText


        var bazarImageView: ImageView = binding.photoBazarImage

        var phoneTextView: TextView = binding.phoneBazarText

        var btn: Button = binding.btnDeleteItemBazar

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ActivityMyItemBazarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.titleTextView.text = item.title
        holder.descriptionTextView.text = item.description
        holder.priceTextView.text = item.price
        holder.btn.setOnClickListener {
            deleteItem(item.id, position)
        }

        val str = "+380" + item.pnumber
        holder.phoneTextView.text = str
        val imagePath = "" +
                list[position].photo
        if (context != null) {
            Glide.with(context)
                .load(imagePath)
                .into(holder.bazarImageView)
        }

    }

    override fun getItemCount(): Int = list.size

    private fun deleteItem(id: Int, position:Int) {
        val sharedPref = context?.getSharedPreferences(
            java.lang.String.valueOf(R.string.shared_preferences_user_data),
            AppCompatActivity.MODE_PRIVATE
        )
        val token = sharedPref?.getString("token", "token")!!

        CoroutineScope(Dispatchers.IO).launch {
            try {
                RetroInstanceMyKowel.getRetroInstance().deleteItemBazar(token,id)
                withContext(Dispatchers.Main) {
                    // Оновлення інтерфейсу користувача або виконання інших необхідних дій після успішного видалення
                    Toast.makeText(context,"Ваше оголошення було видалене", Toast.LENGTH_SHORT).show()
                    notifyItemRemoved(position)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Обробка помилок
                    Toast.makeText(context,"Помилка у видалені оголошення. Спробуйте ще раз", Toast.LENGTH_SHORT).show()

                }
            }
        }

    }


}