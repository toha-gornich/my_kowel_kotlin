package com.cl.mykowel.view.fragments.contacts

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

    fun getContacts() {
        viewModelScope.launch {
            myResponseList.postValue(RetroInstanceMyKowel.getRetroInstance().getItemContact())
        }
    }
}