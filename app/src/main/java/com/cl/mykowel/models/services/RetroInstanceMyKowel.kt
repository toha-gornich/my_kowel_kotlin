package com.cl.mykowel.models.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Returns Retrofit for the API http://mykowel.pp.ua:8000/

class RetroInstanceMyKowel{
    companion object{
        private const val BASE_URL_MY_KOWEL: String = "http://mykowel.pp.ua:8000/"
        fun getRetroInstance(): ApiService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)

            return Retrofit.Builder()
                .baseUrl(BASE_URL_MY_KOWEL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
        }

    }
}





