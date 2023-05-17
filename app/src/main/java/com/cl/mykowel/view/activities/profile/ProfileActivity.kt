package com.cl.mykowel.view.activities.profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cl.mykowel.R
import com.cl.mykowel.databinding.ActivityProfileBinding
import com.cl.mykowel.view.activities.authorization.AuthorizationActivity
import java.lang.String


class ProfileActivity : AppCompatActivity() {
    private var binding: ActivityProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val actionBar = supportActionBar
        actionBar?.title = "Профіль"
        binding?.btnExit?.setOnClickListener {
            exitWithAccount()
        }
        binding?.btnDeleteAcount?.setOnClickListener {
            exitWithAccount()
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

}