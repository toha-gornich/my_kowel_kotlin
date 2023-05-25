package com.cl.mykowel.view.activities.newBazar

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cl.mykowel.R
import com.cl.mykowel.models.model_itemBazar.ItemBazar
import com.cl.mykowel.models.model_itemBazar.AddNewItemBazar
import com.cl.mykowel.models.services.RetroInstanceMyKowel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.lang.String

class AddNewItemBazarActivityViewModel :
    ViewModel() {
    private val myResponseList: MutableLiveData<List<ItemBazar>> = MutableLiveData()

    fun addItemBazar(addNewItemBazarActivity: AddNewItemBazarActivity, itemBazar: AddNewItemBazar) {

        val title = createPartFromString(itemBazar.title)
        val description = createPartFromString(itemBazar.description)
        val price = createPartFromString(itemBazar.price)
        val category = createPartFromString(itemBazar.category)

        val sharedPref = addNewItemBazarActivity.getSharedPreferences(
            String.valueOf(R.string.shared_preferences_user_data),
            AppCompatActivity.MODE_PRIVATE
        )

        val file = File(itemBazar.photo)
//        val requestFile: RequestBody = file
//            .asRequestBody("image/*".toMediaType())
//        val photo = MultipartBody.Part.createFormData("file", file.name, requestFile)

        val photo = MultipartBody.Part.createFormData("file", file.name,
            file.asRequestBody("image/*".toMediaTypeOrNull())
        )

        val token = sharedPref.getString("token", "token")
        viewModelScope.launch {
//            try {
                val response = RetroInstanceMyKowel.getRetroInstance().addItemBazar(token!!, title, description, price, category, photo)
                myResponseList.postValue(response)
//            } catch (e: Exception) {
//                // Обробка помилок
//                // Наприклад, виведення повідомлення про помилку
//                e.printStackTrace()
//            }
        }
    }

    private fun createPartFromString(descriptionString: kotlin.String): RequestBody {
        return descriptionString.toRequestBody(MultipartBody.FORM)
    }
}