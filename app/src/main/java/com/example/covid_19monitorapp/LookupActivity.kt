package com.example.covid_19monitorapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19monitorapp.Adapter.LookUpAdapter
import com.example.covid_19monitorapp.Data.LookUpData
import kotlinx.android.synthetic.main.activity_lookup.*

class LookupActivity: AppCompatActivity() {

    companion object{
        const val Extra="Extras"

        private val mockLookUpList = mutableListOf(
            LookUpData("DKI Jakarta",800,500,300),
            LookUpData("Sumatera Utara",120,80,30),
            LookUpData("Sumatera Selatan",50,60,85),
            LookUpData("Sumatera Barat",959,26,5),
        )

        val lookUpAdapter = LookUpAdapter(mockLookUpList)
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