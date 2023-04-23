package com.cl.mykowel.view.fragments.bazar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cl.mykowel.models.ItemBazar
import com.cl.mykowel.models.services.RetroInstanceMyKowel
import kotlinx.coroutines.launch

class BazarFragmentViewModel: ViewModel() {
    val myResponseList:MutableLiveData<List<ItemBazar>> = MutableLiveData()

    fun getItemBazar(){
        viewModelScope.launch {
            myResponseList.postValue(RetroInstanceMyKowel.getRetroInstance().getItemBazar())
        }
    }
}
