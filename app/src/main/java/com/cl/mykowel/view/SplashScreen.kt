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
        android.os.Handler().postDelayed(object : Runnable {
            override fun run() {
                //This method will be executed once the timer is over
                // Start your app main activity
                haveAccount()


                // close this activity
                finish()
            }
        }, 4000)
    }

    //відкривається SharedPreferences і якшо в нього є збережений токен тоді запускається MainScreen а якшо нема тоді greeting
    private fun haveAccount() {
        val sharedPref = getSharedPreferences(
            String.valueOf(R.string.shared_preferences_user_data),
            MODE_PRIVATE
        )
        //перевірка на наявність ключа "token"
        Log.d("token", sharedPref.all.toString())
        if (sharedPref.contains("token")) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this@SplashScreen, GreetingActivity::class.java))
        }
    }
}