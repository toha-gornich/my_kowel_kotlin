package com.cl.mykowel.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cl.mykowel.R
import com.cl.mykowel.view.activities.greeting.GreetingActivity
import com.cl.mykowel.view.activities.home.MainActivity
import java.lang.String

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spash_screen)
        supportActionBar?.hide()
        android.os.Handler().postDelayed({
            haveAccount()
            // close this activity
            finish()
        }, 4000)
    }

    //SharedPreferences file is opened, and if it contains a saved token, the MainScreen is launched. If it doesn't exist, a GreetingActivity
    private fun haveAccount() {
        val sharedPref = getSharedPreferences(
            String.valueOf(R.string.shared_preferences_user_data),
            MODE_PRIVATE
        )
        //Token presence check."
        if (sharedPref.contains("token")) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this@SplashScreen, GreetingActivity::class.java))
        }
    }
}