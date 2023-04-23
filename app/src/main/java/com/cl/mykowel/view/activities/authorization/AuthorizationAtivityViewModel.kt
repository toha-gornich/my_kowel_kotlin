package com.cl.mykowel.view.activities.authorization

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cl.mykowel.R
import com.cl.mykowel.models.model_user.User
import com.cl.mykowel.models.services.ApiService
import com.cl.mykowel.models.services.RetroInstanceMyKowel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthorizationAtivityViewModel : ViewModel() {
    var createNewUserLiveData: MutableLiveData<User?> = MutableLiveData()


    fun getCreateNewUserObserver(): MutableLiveData<User?> {
        return createNewUserLiveData
    }


    fun createNewUser(context: Context, user: User) {
        val retroService = RetroInstanceMyKowel.getRetroInstance()


        val call = retroService.postUserLogin(
            createPartFromString(user.login.toString()),
            createPartFromString(user.password.toString())
        )
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                createNewUserLiveData.postValue(null)

            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                    val user1: User? = response.body()

                    val sharedPref: SharedPreferences = context.getSharedPreferences(
                        (R.string.shared_preferences_user_data).toString(), Context.MODE_PRIVATE)

                    val editor:SharedPreferences.Editor = sharedPref.edit().clear()
                    editor.putBoolean("account_created", true);
                    editor.putString("id", user1?.id.toString());
                    editor.putString("login", user1?.login)
                    editor.putString("name", user1?.name)
                    editor.putString("phone", user1?.phone)
                    editor.putString("token", user1?.token)
                    editor.putString("is_admin", user1?.isAdmin)
                    editor.apply()
                } else {
                    createNewUserLiveData.postValue(null)
                }
            }
        })
    }

    private fun createPartFromString(descriptionString: String): RequestBody {
        return RequestBody.create(MultipartBody.FORM, descriptionString)
    }

}