package com.cl.mykowel.view.activities.reg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cl.mykowel.view.activities.authorization.AuthorizationActivity
import com.cl.mykowel.view.activities.home.MainActivity
import com.cl.mykowel.databinding.ActivityRegBinding
import com.cl.mykowel.models.model_user.UserDataModel
import com.cl.mykowel.models.model_user.UserRegModel
import com.google.android.material.textfield.TextInputEditText

class RegActivity : AppCompatActivity() {
    private lateinit var viewModel: RegActivityViewModel
    private lateinit var binding: ActivityRegBinding

    private lateinit var loginEditText: TextInputEditText
    private lateinit var nameEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var phoneEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        val actionBar = supportActionBar
        actionBar?.title = "Реєстрація"
        binding.textBtnLoginTextView.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AuthorizationActivity::class.java)
            startActivity(intent)

        })
        binding.floatingActionButtonReg.setOnClickListener(View.OnClickListener {
            createUser()

        })
        loginEditText = binding.loginTextInputEditTextLogin
        nameEditText = binding.loginTextInputEditTextName
        emailEditText = binding.loginTextInputEditTextEmail
        phoneEditText = binding.loginTextInputEditTextPhone
        passwordEditText = binding.loginTextInputEditTextPassword
    }
    private fun createUser() {
        val user =
            UserRegModel(loginEditText.text.toString(),nameEditText.text.toString(),emailEditText.text.toString(),phoneEditText.text.toString(), passwordEditText.text.toString())

        viewModel.createNewUser(this, user)

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(RegActivityViewModel::class.java)
        viewModel.getCreateNewUserObserver().observe(this, Observer<UserDataModel?> {

            if (it == null) {
                Toast.makeText(
                    this@RegActivity,
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