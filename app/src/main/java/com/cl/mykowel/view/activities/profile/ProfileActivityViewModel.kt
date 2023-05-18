package com.cl.mykowel.view.activities.profile

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cl.mykowel.R
import com.cl.mykowel.models.ItemBazar
import com.cl.mykowel.models.services.RetroInstanceMyKowel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ProfileActivityViewModel() : ViewModel() {
    val myResponseList: MutableLiveData<ArrayList<ItemBazar>> = MutableLiveData()

    fun deleteUser(profileActivity: ProfileActivity) {
        val token = initSharedPref(profileActivity)

        viewModelScope.launch {
            RetroInstanceMyKowel.getRetroInstance().deleteUser(token)
        }
    }

    fun getMyItemBazar(profileActivity: ProfileActivity) {
        val token = initSharedPref(profileActivity)
//        Log.d("tokenGet", token)

        viewModelScope.launch {
            myResponseList.postValue(RetroInstanceMyKowel.getRetroInstance().getMyItemBazar(token))
        }
    }

    private fun initSharedPref(profileActivity: ProfileActivity): String {
        val sharedPref = profileActivity.getSharedPreferences(
            java.lang.String.valueOf(R.string.shared_preferences_user_data),
            AppCompatActivity.MODE_PRIVATE
        )
        return sharedPref.getString("token", "token")!!
    }

//    fun deleteUser(id: String) {
//        val token = initSharedPref(ProfileActivity())
//
//        viewModelScope.launch {
//            RetroInstanceMyKowel.getRetroInstance().deleteItemBazar(token,createPartFromString(id))
//        }
//    }
//    private fun createPartFromString(descriptionString: kotlin.String): RequestBody {
//        return RequestBody.create(MultipartBody.FORM, descriptionString)
//    }
}