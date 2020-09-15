package com.example.covid_19monitorapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19monitorapp.Adapter.LookUpAdapter
import kotlinx.android.synthetic.main.activity_lookup.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.lang.Exception

class LookupActivity: AppCompatActivity() {

    companion object{
        const val Extra="Extras"
        val okHttpClient = OkHttpClient()
    }

    private val listOfLookUpData = mutableListOf<LookUpData>(
        LookUpData("Dummy Data", 100, 100, 100)
    )
    private val lookUpAdapter = LookUpAdapter(listOfLookUpData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lookup)
        title = "Lookup Activity"
        val request = Request.Builder().url("https://api.kawalcorona.com/indonesia/provinsi/#").build()

        rvLookUp.layoutManager = LinearLayoutManager(this)
        rvLookUp.adapter = lookUpAdapter

        reqLookUpData(request)

        backButton.setOnClickListener{
            onBackPressed()
        }
        ibDelInputButton.setOnClickListener {
            if(!etSearchInput.text.isEmpty()) { etSearchInput.text.clear() }
        }
        etSearchInput.addTextChangedListener(searchTextChangeListener(request))
    }

    private fun searchTextChangeListener(request: Request) : TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("Search", "Before Text Changed")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("Search", "On Text Changed")
            }

            override fun afterTextChanged(p0: Editable?) {
                this@LookupActivity.runOnUiThread {
                    if(!p0.toString().isNullOrBlank()) {
//                        reqLookUpData(request)
                        lookUpAdapter.filterAndUpdateData(p0.toString())
                    } else {
                        reqLookUpData(request)
                    }
                }
            }
        }
    }


    private fun reqLookUpData(request: Request) {
        okHttpClient
            .newCall(request)
            .enqueue(getCallback(lookUpAdapter))
    }

    private fun getCallback(lookUpAdapter: LookUpAdapter): Callback {
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
                    val lookUpListFromNetwork = mutableListOf<LookUpData>()

                    for (i in 0 until jsonArray.length()){
                        lookUpListFromNetwork.add(
                            LookUpData(
                                jsonArray.getJSONObject(i).getJSONObject("attributes").getString("Provinsi"),
                                jsonArray.getJSONObject(i).getJSONObject("attributes").getInt("Kasus_Posi"),
                                jsonArray.getJSONObject(i).getJSONObject("attributes").getInt("Kasus_Semb"),
                                jsonArray.getJSONObject(i).getJSONObject("attributes").getInt("Kasus_Meni")
                            )
                        )
                    }
                    this@LookupActivity.runOnUiThread {
                        lookUpAdapter.updateData(lookUpListFromNetwork)
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