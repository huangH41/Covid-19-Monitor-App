package com.example.covid_19monitorapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19monitorapp.Adapter.HotlineAdapter
import com.example.covid_19monitorapp.Adapter.LookUpAdapter
import kotlinx.android.synthetic.main.activity_lookup.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.lookUpArrIcon
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.lang.Exception

class LookupActivity: AppCompatActivity() {

    companion object{
        const val Extra="Extras"
        val okHttpClient = OkHttpClient()

        private val mockLookUpList = mutableListOf(
            LookUpData("DKI Jakarta",800,500,300),
            LookUpData("Sumatera Utara",120,80,30),
            LookUpData("Sumatera Selatan",50,60,85),
            LookUpData("Sumatera Barat",959,26,5),
        )

        private val mockHotlineList = mutableListOf(
            HotlineData(name = "Loading..", imgIcon = "", phone="")
        )

        val lookUpAdapter = LookUpAdapter(mockLookUpList)
        val hotlineAdapter = HotlineAdapter(mockHotlineList)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lookup)
        title= "Lookup Activity"

        val value = intent.getStringExtra(Extra)
        search.setQuery(value,false)

        backButton.setOnClickListener{
            onBackPressed()
        }

        rvLookUp.layoutManager =LinearLayoutManager(this)
        rvLookUp.adapter = lookUpAdapter

//        rvLookUp.layoutManager = LinearLayoutManager(this)
//        rvLookUp.adapter = hotlineAdapter

        val request = Request.Builder().url("https://bncc-corona-versus.firebaseio.com/v1/hotlines.json")
            .build()

        okHttpClient
            .newCall(request)
            .enqueue(getCallback(hotlineAdapter))
    }

    private fun getCallback(hotlineAdapter: HotlineAdapter): Callback {
        return object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                this@LookupActivity.runOnUiThread {
                    Toast.makeText(this@LookupActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonString = response.body?.string()
                    val jsonArray = JSONArray(jsonString)
                    val hotlineListFromNetwork = mutableListOf<HotlineData>()

                    for (i in 0 until jsonArray.length()){
                        hotlineListFromNetwork.add(
                            HotlineData(
                                imgIcon = jsonArray.getJSONObject(i).getString("img_icon"),
                                name = jsonArray.getJSONObject(i).getString("name"),
                                phone = jsonArray.getJSONObject(i).getString("phone"))
                        )

                    }
                    this@LookupActivity.runOnUiThread {
                        hotlineAdapter.updateData(hotlineListFromNetwork)
                    }
                }catch (e: Exception){
                    this@LookupActivity.runOnUiThread {
                        Toast.makeText(this@LookupActivity, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

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

}