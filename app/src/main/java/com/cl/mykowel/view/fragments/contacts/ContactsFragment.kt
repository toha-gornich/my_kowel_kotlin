package com.cl.mykowel.view.fragments.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cl.mykowel.databinding.FragmentContactsListBinding
import com.cl.mykowel.view.fragments.adapters.ContactsRecyclerViewAdapter


class ContactsFragment : Fragment() {

    private var binding: FragmentContactsListBinding? = null
//    private var newsList = List<ItemContact>()
    private var adapter: ContactsRecyclerViewAdapter? = null

    private val viewModel: ContactsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsListBinding.inflate(inflater, container, false)
        val view = binding!!.root
//        val viewModel = ViewModelProvider(this).get(ContactsFragmentViewModel::class.java)
        viewModel.getContacts(requireContext())
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