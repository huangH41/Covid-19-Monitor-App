package com.example.covid_19monitorapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.covid_19monitorapp.Adapter.TotalCaseAdapter
import com.example.covid_19monitorapp.Data.TotalData
import com.example.covid_19monitorapp.network.HomeService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.dialog_hotline.*
import kotlinx.android.synthetic.main.item_lookup.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object{
        const val Extra="Extras"
        val hotlineFragment = HotlineFragment()
        val infoFragment = InfoFragment()
        private val retrofit = Retrofit.Builder().baseUrl("https://api.kawalcorona.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        private val mockTotalDataList = mutableListOf(TotalData("Country",999999,999999,999999,999999))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("Activity","Activity:onCreate")

        hotlineBottomSheet.visibility = View.GONE

        lookUpArrIcon.setOnClickListener(){
            lookUpActivity()
        }
        ibInfo.setOnClickListener(){
            infoFragment.show(supportFragmentManager,"infoDialog")
        }
        hotlineArrIcon.setOnClickListener(){
            hotlineFragment.show(supportFragmentManager, "HotlineDialog")
        }
        val totalCaseAdapter = TotalCaseAdapter(mockTotalDataList)
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val totalData = retrofit.create(HomeService::class.java).getTotalCase()
                withContext(Dispatchers.IO){
//                    Log.e('Activity',"${totalData}")

                     totalCaseAdapter.updateData(totalData)
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_SHORT).show()
                    totalCaseAdapter.updateData(listOf())
                }
            }
        }
    }

    fun bind(data: TotalData){
        tvCountry.text = data.country
        totalCase.text = "${data.totalCase}"
        totalPositive.text = "${data.totatlPositif}"
        totalRecovered.text = "${data.totalRecovered}"
        totalDeath.text = "${data.totalDead}"
    }


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onStart() {
        super.onStart()
        Log.e("Activity","Activity:onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Activity","Activity:onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Activity","Activity:onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("Activity","Activity:onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("Activity","Activity:onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Activity","Activity:onDestroy")
    }
//    Explicit Intent
    private fun lookUpActivity(){
        val intent =Intent(this, LookupActivity::class.java).apply{
            putExtra(Extra,"Lampung")
        }
        startActivity(intent)
    }
//    Implicit Intent
        fun phoneCall(context: Context, request: String){
            Log.e("phone","$request")
            val phoneNumber = request.replace("-","")
            Log.e("phone","$phoneNumber")
            val intent = Intent().apply{
                action = Intent.ACTION_DIAL
                data = Uri.parse("tel:$phoneNumber" )
            }
                context.startActivity(intent)
        }
}