package com.example.tsj.api
import com.example.tsj.`interface`.apirequest
import com.example.tsj.utils.constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    val api : apirequest by lazy {
        retrofit.create(apirequest::class.java)
    }
}