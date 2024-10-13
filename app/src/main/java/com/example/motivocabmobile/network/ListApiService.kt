package com.example.motivocabmobile.network

import com.example.motivocabmobile.model.Word
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.http.GET

private const val BASE_URL = "https://script.google.com"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
//    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface ListApiService {
    @GET("macros/s/AKfycbxpRa_v-jGsal5hio9hEtTcns2XdlFFMCIrVtgkQFlzRCAnRnUNUi6fROEyTMsh9yaxzA/exec")
    suspend fun getList(): String
//    suspend fun getList(): List<Word>
}

object ListApi{
    val retrofitService : ListApiService by lazy{
        retrofit.create(ListApiService::class.java)
    }
}