package com.cl.mykowel.view.activities.greeting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cl.mykowel.R
import com.cl.mykowel.view.activities.authorization.AuthorizationActivity

class GreetingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)
        val actionBar = supportActionBar
        actionBar?.title = "My Kowel"
    }

    fun PushToLoginScreen(view: View) {
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent)
    }
}