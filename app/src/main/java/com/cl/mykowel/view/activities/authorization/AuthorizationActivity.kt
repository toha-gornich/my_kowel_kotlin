package com.cl.mykowel.view.activities.authorization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cl.mykowel.view.activities.home.MainActivity
import com.cl.mykowel.view.activities.reg.RegActivity
import com.cl.mykowel.databinding.ActivityAuthorizationBinding
import com.cl.mykowel.models.model_user.User
import com.google.android.material.textfield.TextInputEditText


class AuthorizationActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthorizationAtivityViewModel
    private lateinit var binding: ActivityAuthorizationBinding
    private lateinit var loginEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        val actionBar = supportActionBar
        actionBar?.title = "Вхід"
        binding.textBtnRegTextView.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this, RegActivity::class.java)
            startActivity(intent)

        })
        binding.floatingActionButtonLogin.setOnClickListener(View.OnClickListener {
            createUser()

        })
        loginEditText = binding.loginEditText
        passwordEditText = binding.passwordEditText
    }

    private fun createUser() {
        val user =
            User(loginEditText.text.toString(), passwordEditText.text.toString())

        viewModel.createNewUser(this@AuthorizationActivity,user)

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AuthorizationAtivityViewModel::class.java)
        viewModel.getCreateNewUserObserver().observe(this, Observer<User?> {

            if (it == null) {
                Toast.makeText(
                    this@AuthorizationActivity,
                    "Failed to create User",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
        })
    }

}