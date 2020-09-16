package com.example.covid_19monitorapp.network

import com.example.covid_19monitorapp.Data.TotalData
import retrofit2.http.GET

interface HomeService {
    @GET("https://api.kawalcorona.com/indonesia/")
    suspend fun getTotalCase(): List<TotalData>
}