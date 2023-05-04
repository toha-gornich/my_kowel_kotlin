package com.cl.mykowel.view.fragments.other


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cl.mykowel.*
import com.cl.mykowel.databinding.FragmentOtherBinding
import com.cl.mykowel.view.activities.chats.ChatsActivity
import com.cl.mykowel.view.activities.myItemBazar.MyItemsBazarActivity
import com.cl.mykowel.view.activities.profile.ProfileActivity


class OtherFragment : Fragment() {


    var binding: FragmentOtherBinding? = null
    private var arrayList: ArrayList<FOtherButtonModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtherBinding.inflate(inflater, container, false)

        val imageId = intArrayOf(R.drawable.ic_call, R.drawable.ic_call, R.drawable.ic_call)
        val title = arrayOf("Профіль", "Чати", "Мої оголошення")
        val description = arrayOf("description", "description", "description")

        for (i in title.indices) {

            val button = FOtherButtonModel(title[i], description[i], imageId[i])
            arrayList.add(button)
        }

        binding!!.fragmentOtherLW.isClickable = true
        binding!!.fragmentOtherLW.adapter = FOtherButtonsAdapter(requireContext(), arrayList)

        lateinit var intent: Intent


        binding!!.fragmentOtherLW.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    intent = Intent(context, ProfileActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                    intent = Intent(context, ChatsActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    intent = Intent(context, MyItemsBazarActivity::class.java)
                    startActivity(intent)
                }
            }


        }

        // Inflate the layout for this fragment
        return binding!!.root
    }


}