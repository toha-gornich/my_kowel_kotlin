package com.cl.mykowel.view.fragments.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cl.mykowel.models.model_news.NewsResponse
import com.cl.mykowel.models.services.RetroInstanceKowelMedia
import kotlinx.coroutines.launch

class HomeFragmentViewModel : ViewModel() {

    val myResponseList: MutableLiveData<NewsResponse> = MutableLiveData()

    fun getNews(context: Context?) {


            viewModelScope.launch {
                try {
                    myResponseList.postValue(
                        RetroInstanceKowelMedia.getRetroInstance().getItemsNews()
                    )

                } catch (e: Exception) {
                    Toast.makeText(context, "Не вдається завантажити сторінку", Toast.LENGTH_SHORT)
                        .show()
                }
//            }
        }

    }



}

