package com.example.covid_19monitorapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19monitorapp.Adapter.LookUpAdapter
import kotlinx.android.synthetic.main.activity_lookup.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.lookUpArrIcon
import java.util.*

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
//        svProvinceSearch.setQuery(value,false)

        backButton.setOnClickListener{
            onBackPressed()
        }

        svProvinceSearch.setOnQueryTextListener(searchOnTextChange(mockLookUpList))

        rvLookUp.layoutManager =LinearLayoutManager(this)
        rvLookUp.adapter = lookUpAdapter
    }


    private fun searchOnTextChange(listOfLookUpData : MutableList<LookUpData>) : SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                filterLookUpData(listOfLookUpData, p0)
                return true;
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false;
            }
        }
    }

    private fun filterLookUpData(listOfLookUpData: MutableList<LookUpData>, keyword : String?) {

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