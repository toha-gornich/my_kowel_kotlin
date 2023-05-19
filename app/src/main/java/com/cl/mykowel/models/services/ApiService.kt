package com.cl.mykowel.models.services

import com.cl.mykowel.models.model_contact.ItemContact
import com.cl.mykowel.models.model_itemBazar.ItemBazar
import com.cl.mykowel.models.model_news.NewsResponse
import com.cl.mykowel.models.model_user.UserDataModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //"GET request for retrieving a list of news

    @GET("feed/json")
    suspend fun getItemsNews(): NewsResponse

    //POST request for user authentication

    @POST("login")
    @Multipart
    fun postUserLogin(
        @Part("login") login: RequestBody,
        @Part("password") password: RequestBody
    ): Call<UserDataModel>

    //DELETE request for delete user

    @DELETE("user/delete")
    suspend fun deleteUser(@Header("token") token: String)

    //POST request for user register

    @POST("register")
    @Multipart
    fun postUserReg(
        @Part("login") login: RequestBody,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("password") password: RequestBody
    ): Call<UserDataModel>

    //"GET request for retrieving a list of contacts

    @GET("contacts/get")
    suspend fun getItemContact(): List<ItemContact>

    //"GET request for retrieving a list of items bazar

    @GET("market/get")
    suspend fun getItemBazar(): List<ItemBazar>

    //POST request for add new item bazar

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

    //"GET request for retrieving a list of my items bazar

    @GET("market/get/user")
    suspend fun getMyItemBazar(@Query("value") token: String): ArrayList<ItemBazar>

    //DELETE request for delete item bazar

    @DELETE("market/delete")
    suspend fun deleteItemBazar(
        @Header("token") token: String,
        @Header("id") id: Int
    )
}


