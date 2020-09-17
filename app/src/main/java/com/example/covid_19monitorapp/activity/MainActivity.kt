package com.example.covid_19monitorapp.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.example.covid_19monitorapp.data.CountryTotalCaseData
import com.example.covid_19monitorapp.fragment.HotlineFragment
import com.example.covid_19monitorapp.fragment.InfoFragment
import com.example.covid_19monitorapp.R
import com.example.covid_19monitorapp.contract.HomeContract
import com.example.covid_19monitorapp.network.HomeRetrofitService
import com.example.covid_19monitorapp.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_hotline.*

class MainActivity : AppCompatActivity(), HomeContract.View {

    companion object {
        val hotlineFragment = HotlineFragment()
        val infoFragment = InfoFragment()
    }

    private val presenter = HomePresenter(this)
    private val retrofitService = HomeRetrofitService.createHomeService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hotlineBottomSheet.visibility = View.GONE
        presenter.reqTotalCountryCaseData(retrofitService)
        setViewEventListener()
    }

    override fun bindData(caseDataCountry: CountryTotalCaseData) {
        tvCountry.text = caseDataCountry.country
        totalCase.text = caseDataCountry.totalCase
        totalPositive.text = caseDataCountry.totatlPositif
        totalRecovered.text = caseDataCountry.totalRecovered
        totalDeath.text = caseDataCountry.totalDead
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun lookUpActivity() {
        val intent = Intent(this, LookupActivity::class.java)
        startActivity(intent)
    }

    private fun setViewEventListener() {
        lookUpButton.setOnClickListener() {
            lookUpActivity()
        }

        lookUpArrIcon.setOnClickListener() {
            lookUpActivity()
        }

        ibInfo.setOnClickListener() {
            infoFragment.show(supportFragmentManager, "infoDialog")
        }

        hotlineButton.setOnClickListener() {
            hotlineFragment.show(supportFragmentManager, "HotlineDialog")
        }

        hotlineArrIcon.setOnClickListener() {
            hotlineFragment.show(supportFragmentManager, "HotlineDialog")
        }
    }

    fun phoneCall(context: Context, request: String) {
        val phoneNumber = request.replace("-", "")
        val intent = Intent().apply {
            action = Intent.ACTION_DIAL
            data = Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(intent)
    }
}