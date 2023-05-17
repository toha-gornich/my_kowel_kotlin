package com.cl.mykowel.models.services

import com.cl.mykowel.models.ItemBazar
import com.cl.mykowel.models.model_contact.ItemContact
import com.cl.mykowel.models.model_news.NewsResponse
import com.cl.mykowel.models.model_user.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("feed/json")
    suspend fun getItemsNews(): NewsResponse

    @POST("login")
    @Multipart
    fun postUserLogin(
        @Part("login") login: RequestBody,
        @Part("password") password: RequestBody
    ): Call<User>


    @DELETE("user/delete")
    suspend fun deleteUser(@Header("token") token: String)

    @POST("register")
    @Multipart
    fun postUserReg(
        @Part("login") login: RequestBody,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("password") password: RequestBody
    ): Call<User>

    @GET("contacts/get")
    suspend fun getItemContact(): List<ItemContact>

    @GET("market/get")
    suspend fun getItemBazar(): List<ItemBazar>

    @POST("market/add")
    @Multipart
    suspend fun addItemBazar(
        @Header("token") token: String,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("price") price: RequestBody,
        @Part("category") category: RequestBody,
        @Part photo: MultipartBody.Part
    ): List<ItemBazar>

}


