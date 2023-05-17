package com.cl.mykowel.view.activities.profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cl.mykowel.R
import com.cl.mykowel.databinding.ActivityProfileBinding
import com.cl.mykowel.view.activities.authorization.AuthorizationActivity
import com.cl.mykowel.view.activities.newBazar.AddNewItemBazarActivityViewModel
import java.lang.String


class ProfileActivity : AppCompatActivity() {
    private var binding: ActivityProfileBinding? = null
    private var viewModel: ProfileActivityViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViewModel()
        val actionBar = supportActionBar
        actionBar?.title = "Профіль"
        binding?.btnExit?.setOnClickListener {
            exitWithAccount()
        }
        binding?.btnDeleteAcount?.setOnClickListener {
            deleteAccount()
        }
    }

    private fun exitWithAccount(){
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
    private fun deleteAccount(){

        viewModel?.deleteUser(this@ProfileActivity)
        exitWithAccount()
    }
    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ProfileActivityViewModel::class.java]
    }
}