package com.cl.mykowel.view.fragments.contacts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cl.mykowel.R
import com.cl.mykowel.databinding.FragmentContactsListBinding
import com.cl.mykowel.databinding.FragmentHomeListBinding
import com.cl.mykowel.models.model_contact.ItemContact
import com.cl.mykowel.models.model_news.ItemNews
import com.cl.mykowel.view.fragments.adapters.ContactsRecyclerViewAdapter
import com.cl.mykowel.view.fragments.adapters.HomeRecyclerViewAdapter
import com.cl.mykowel.view.fragments.home.HomeFragmentViewModel
import com.cl.mykowel.view.fragments.placeholder.PlaceholderContent


class ContactsFragment : Fragment() {

    private var binding: FragmentContactsListBinding? = null
//    private var newsList = List<ItemContact>()
    private var adapter: ContactsRecyclerViewAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsListBinding.inflate(inflater, container, false)
        val view = binding!!.root
        val viewModel = ViewModelProvider(this).get(ContactsFragmentViewModel::class.java)
        viewModel.getContacts()
        viewModel.myResponseList.observe(viewLifecycleOwner, Observer {
//            newsList = it
            adapter = ContactsRecyclerViewAdapter(it, context)
            init()
        })


        return view
    }

    private fun init() {
        binding?.apply {
            list.layoutManager = LinearLayoutManager(context)
            list.adapter = adapter
        }
    }
}