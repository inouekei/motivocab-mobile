package com.example.motivocabmobile.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://script.google.com/macros/s/AKfycbxpRa_v-jGsal5hio9hEtTcns2XdlFFMCIrVtgkQFlzRCAnRnUNUi6fROEyTMsh9yaxzA/exec"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ListApiService {
    @GET
    suspend fun getList(): String
}

object ListApi{
    val retrofitService : ListApiService by lazy{
        retrofit.create(ListApiService::class.java)
    }
}