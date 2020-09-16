package com.example.covid_19monitorapp.data

data class TotalData (
    val country: String,
    val totalCase: Int,
    val totatlPositif: Int,
    val totalRecovered: Int,
    val totalDead: Int
)
