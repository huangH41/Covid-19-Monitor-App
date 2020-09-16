package com.example.covid_19monitorapp.adapter

import com.example.covid_19monitorapp.data.TotalData

class TotalCaseAdapter(private val totalDataList: MutableList<TotalData>) {

    fun updateData(newList: List<TotalData>) {
        totalDataList.clear()
        totalDataList.addAll(newList)
    }

}