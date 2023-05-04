package com.cl.mykowel.view.fragments.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cl.mykowel.R
import com.cl.mykowel.databinding.FragmentHomeBinding
import com.cl.mykowel.databinding.FragmentHomeListBinding
import com.cl.mykowel.models.model_news.ItemNews
import com.cl.mykowel.models.model_news.NewsResponse
import com.cl.mykowel.view.fragments.adapters.HomeRecyclerViewAdapter
import com.cl.mykowel.view.fragments.contacts.ContactsFragmentViewModel
import com.cl.mykowel.view.fragments.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeListBinding
    private var newsList = ArrayList<ItemNews>()
    private lateinit var adapter:HomeRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeListBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        viewModel.getNews(requireContext())
        viewModel.myResponseList.observe(viewLifecycleOwner, Observer {
//            Log.d("TAGS", it.items.get(0).title)
            newsList = it.items
            adapter = HomeRecyclerViewAdapter(newsList, context)
            init()
        })
        // Set the adapter
        return view
    }

    private fun init() {
        binding.apply {
            list.layoutManager = LinearLayoutManager(context)
            list.adapter = adapter
        }
    }
    override fun startActivity(intent: Intent) {
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // Якщо не знайдено додаток, який може обробити цей інтент
            Toast.makeText(context, "Не знайдено додаток, щоб обробити цей запит", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            // Якщо сталась якась інша помилка
            Toast.makeText(context, "Сталась помилка: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

}