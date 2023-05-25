package com.cl.mykowel.view.fragments.other


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cl.mykowel.*
import com.cl.mykowel.databinding.FragmentOtherBinding
import com.cl.mykowel.view.activities.poster.PosterActivity


class OtherFragment : Fragment() {


    var binding: FragmentOtherBinding? = null
    private var arrayList: ArrayList<FOtherButtonModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtherBinding.inflate(inflater, container, false)

        val imageId = intArrayOf(R.drawable.ic_personalcard)
        val title = arrayOf("Афіша подій")
        val description = arrayOf("Події в місті Ковель")

        for (i in title.indices) {

            val button = FOtherButtonModel(title[i], description[i], imageId[i])
            arrayList.add(button)
        }

        binding!!.fragmentOtherLW.isClickable = true
        binding!!.fragmentOtherLW.adapter = FOtherButtonsAdapter(requireContext(), arrayList)



        binding!!.fragmentOtherLW.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
//                    intent = Intent(context, PosterActivity::class.java)
//                    startActivity(intent)
                    Toast.makeText(context,"Скоро буде",Toast.LENGTH_SHORT).show()
                }
//                1 -> {
//                    intent = Intent(context, ChatsActivity::class.java)
//                    startActivity(intent)
//                }
//                2 -> {
//                    intent = Intent(context, MyItemsBazarActivity::class.java)
//                    startActivity(intent)
//                }
            }


        }

        // Inflate the layout for this fragment
        return binding!!.root
    }


}