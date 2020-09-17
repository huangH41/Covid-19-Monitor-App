package com.example.covid_19monitorapp.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.covid_19monitorapp.adapter.TotalCaseAdapter
import com.example.covid_19monitorapp.data.CountryTotalCaseData
import com.example.covid_19monitorapp.fragment.HotlineFragment
import com.example.covid_19monitorapp.fragment.InfoFragment
import com.example.covid_19monitorapp.R
import com.example.covid_19monitorapp.network.HomeService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_hotline.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        const val Extra = "Extras"
        val hotlineFragment = HotlineFragment()
        val infoFragment = InfoFragment()
    }

    private val mockTotalDataList = mutableListOf(CountryTotalCaseData("Country", "99", "99", "99", "99"))
    private val totalCaseAdapter = TotalCaseAdapter(mockTotalDataList)

    private val homeService = HomeService.createHomeService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hotlineBottomSheet.visibility = View.GONE

        reqTotalCountryCaseData(homeService)

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

    private fun bindTotalCountryCase(caseDataCountry: CountryTotalCaseData) {
        tvCountry.text = caseDataCountry.country
        totalCase.text = "${caseDataCountry.totalCase}"
        totalPositive.text = "${caseDataCountry.totatlPositif}"
        totalRecovered.text = "${caseDataCountry.totalRecovered}"
        totalDeath.text = "${caseDataCountry.totalDead}"
    }


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun reqTotalCountryCaseData(homeService: HomeService) {
        homeService.getTotalCase().enqueue(object : Callback<List<CountryTotalCaseData>> {
            override fun onResponse(
                call: Call<List<CountryTotalCaseData>>,
                response: Response<List<CountryTotalCaseData>>
            ) {
                bindTotalCountryCase(response?.body()!![0])
            }

            override fun onFailure(call: Call<List<CountryTotalCaseData>>, t: Throwable) {
                this@MainActivity.runOnUiThread {
                    Log.e("TotalCountryDataReq", t.message.toString())
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun lookUpActivity() {
        val intent = Intent(this, LookupActivity::class.java).apply {
            putExtra(Extra, "Lampung")
        }
        startActivity(intent)
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