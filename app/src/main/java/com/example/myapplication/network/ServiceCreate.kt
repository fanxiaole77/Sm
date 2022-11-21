package com.example.myapplication.network

import com.example.myapplication.extensions.sharedPreferences
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreate {

    val ip = sharedPreferences.getString("ip","")
    val dk = sharedPreferences.getString("dk","")
    val url ="http://$ip:$dk/"

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T>create(service:Class<T>):T = retrofit.create(service)
    val smartcityService = create(SmartCityApi::class.java)

}