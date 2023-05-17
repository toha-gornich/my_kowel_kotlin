package com.cl.mykowel.view.activities.profile

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cl.mykowel.R
import com.cl.mykowel.models.services.RetroInstanceMyKowel
import kotlinx.coroutines.launch

class ProfileActivityViewModel: ViewModel() {

    fun deleteUser(profileActivity: ProfileActivity){
        val sharedPref = profileActivity.getSharedPreferences(
            java.lang.String.valueOf(R.string.shared_preferences_user_data),
            AppCompatActivity.MODE_PRIVATE
        )
        val token = sharedPref.getString("token", "token")
        viewModelScope.launch {
                RetroInstanceMyKowel.getRetroInstance().deleteUser(token!!)
        }
    }
}