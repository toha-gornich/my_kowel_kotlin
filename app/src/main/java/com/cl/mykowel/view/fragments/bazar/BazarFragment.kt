package com.cl.mykowel.view.fragments.bazar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cl.mykowel.databinding.FragmentBazarListBinding
import com.cl.mykowel.view.activities.AddNewItemBazarActivity
import com.cl.mykowel.view.fragments.adapters.BazarRecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BazarFragment : Fragment() {
    private var newBazarFAB: FloatingActionButton? = null
    private var binding: FragmentBazarListBinding? = null

    //    private var list: List<ItemBazar>? = null
    private var adapter: BazarRecyclerViewAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //ініціалізація binding
        binding = FragmentBazarListBinding.inflate(inflater, container, false)
        val view = binding!!.root


        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newBazarFAB = binding!!.NewBazarFAB
        newBazarFAB!!.setOnClickListener(View.OnClickListener {


        })

        //ініціалізація ViewModel
        val viewModel = ViewModelProvider(this).get(BazarFragmentViewModel::class.java)

        // get request
        viewModel.getItemBazar()
        viewModel.myResponseList.observe(
            viewLifecycleOwner, Observer
            {
                adapter = BazarRecyclerViewAdapter(it, context)
                init()
            }
        )
        binding!!.NewBazarFAB.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, AddNewItemBazarActivity::class.java)
            startActivity(intent)

//            MAIN.navController.navigate(R.id.addNewItemBazarFragment)

        })
    }

    private fun init() {
        binding?.apply {
            list.layoutManager = LinearLayoutManager(context)
            list.adapter = adapter
        }
    }
}
