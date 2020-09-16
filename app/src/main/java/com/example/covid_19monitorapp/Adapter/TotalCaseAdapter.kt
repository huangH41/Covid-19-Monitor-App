package com.example.covid_19monitorapp.Adapter

import com.example.covid_19monitorapp.Data.TotalData
import com.example.covid_19monitorapp.MainActivity

class TotalCaseAdapter(private val totalDataList: MutableList<TotalData>) {

    fun updateData(newList: List<TotalData>) {
        totalDataList.clear()
        totalDataList.addAll(newList)
    }

}