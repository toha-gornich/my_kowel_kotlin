package com.cl.mykowel.view.activities.profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cl.mykowel.R
import com.cl.mykowel.databinding.ActivityProfileBinding
import com.cl.mykowel.view.activities.authorization.AuthorizationActivity
import java.lang.String
import kotlin.apply


class ProfileActivity : AppCompatActivity() {
    private var adapter: MyItemsBazarAdapter? = null
    private var binding: ActivityProfileBinding? = null
    private var viewModel: ProfileActivityViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViewModel()
        viewModel?.getMyItemBazar(this@ProfileActivity)

        viewModel?.myResponseList?.observe(this, Observer {
            adapter = MyItemsBazarAdapter(it, this@ProfileActivity)
            init()
        })
        val actionBar = supportActionBar
        actionBar?.title = "Профіль"
        binding?.btnExit?.setOnClickListener {
            exitWithAccount()
        }
        binding?.btnDeleteAcount?.setOnClickListener {
            deleteAccount()
        }
    }

    private fun exitWithAccount() {
        val sharedPref = getSharedPreferences(
            String.valueOf(R.string.shared_preferences_user_data),
            MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
        if (!sharedPref.contains("token")) {
            intent = Intent(this, AuthorizationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteAccount() {

        viewModel?.deleteUser(this@ProfileActivity)
        exitWithAccount()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ProfileActivityViewModel::class.java]
    }

    private fun init() {
        binding?.apply {
            rvMyItemsBazar.layoutManager = LinearLayoutManager(this@ProfileActivity)
            rvMyItemsBazar.adapter = adapter
        }
    }
}