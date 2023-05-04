package com.cl.mykowel.view.fragments.contacts

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cl.mykowel.models.model_contact.ItemContact
import com.cl.mykowel.models.model_news.NewsResponse
import com.cl.mykowel.models.services.RetroInstanceKowelMedia
import com.cl.mykowel.models.services.RetroInstanceMyKowel
import kotlinx.coroutines.launch

class ContactsFragmentViewModel : ViewModel() {
    val myResponseList: MutableLiveData<List<ItemContact>> = MutableLiveData()

    fun getContacts(context: Context?) {
        if (isNetworkAvailable(context)){
            viewModelScope.launch {
                myResponseList.postValue(RetroInstanceMyKowel.getRetroInstance().getItemContact())
            }
        }

    }
    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}