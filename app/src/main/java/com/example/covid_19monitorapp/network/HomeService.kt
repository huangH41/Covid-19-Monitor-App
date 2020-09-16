package com.example.covid_19monitorapp.network

import com.example.covid_19monitorapp.data.CountryTotalCaseData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HomeService {
    @GET("indonesia/")
    fun getTotalCase(): Call<List<CountryTotalCaseData>>

    companion object {
        val BASE_URL = "https://api.kawalcorona.com"

        fun createHomeService() : HomeService {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()

            return retrofit.create(HomeService::class.java)
        }
    }
}