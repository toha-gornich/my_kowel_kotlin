package com.cl.mykowel.view.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cl.mykowel.models.model_news.NewsResponse
import com.cl.mykowel.models.services.RetroInstanceKowelMedia
import kotlinx.coroutines.launch

class HomeFragmentViewModel : ViewModel() {

    val myResponseList: MutableLiveData<NewsResponse> = MutableLiveData()

    fun getNews() {
        viewModelScope.launch {
            myResponseList.postValue(RetroInstanceKowelMedia.getRetroInstance().getItemsNews())
        }
    }
}