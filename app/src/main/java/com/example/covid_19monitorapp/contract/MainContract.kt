package com.example.covid_19monitorapp.contract

import com.example.covid_19monitorapp.data.CountryTotalCaseData
import com.example.covid_19monitorapp.network.HomeRetrofitService

interface MainContract {
    interface View {
        fun bindData(caseDataCountry: CountryTotalCaseData)
    }

    interface Presenter {
        fun reqTotalCountryCaseData(homeRetrofitService: HomeRetrofitService)
    }
}