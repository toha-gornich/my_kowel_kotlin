package com.cl.mykowel.view.fragments.bazar

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cl.mykowel.models.model_itemBazar.ItemBazar
import com.cl.mykowel.models.services.RetroInstanceMyKowel
import kotlinx.coroutines.launch

class BazarFragmentViewModel : ViewModel() {
    val myResponseList: MutableLiveData<List<ItemBazar>> = MutableLiveData()

    fun getItemBazar(context: Context) {
        viewModelScope.launch {

            try {
                myResponseList.postValue(RetroInstanceMyKowel.getRetroInstance().getItemBazar())

            } catch (e: Exception) {
                Toast.makeText(context, "Не вдається завантажити сторінку", Toast.LENGTH_SHORT)
                    .show()
            }
        }
//        }
    }


}
