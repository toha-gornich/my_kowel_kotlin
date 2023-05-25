package com.cl.mykowel.view.fragments.contacts

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cl.mykowel.models.model_contact.ItemContact
import com.cl.mykowel.models.services.RetroInstanceMyKowel
import kotlinx.coroutines.launch

class ContactsFragmentViewModel : ViewModel() {
    val myResponseList: MutableLiveData<List<ItemContact>> = MutableLiveData()

    fun getContacts(context: Context?) {
        viewModelScope.launch {

            try {
                myResponseList.postValue(
                    RetroInstanceMyKowel.getRetroInstance().getItemContact()
                )


            } catch (e: Exception) {
                Toast.makeText(context, "Не вдається завантажити сторінку", Toast.LENGTH_SHORT)
                    .show()
            }

        }


//        }

    }


}