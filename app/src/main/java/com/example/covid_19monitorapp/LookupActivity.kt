package com.example.covid_19monitorapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19monitorapp.Adapter.LookUpAdapter
import kotlinx.android.synthetic.main.activity_lookup.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.lookUpArrIcon
import java.util.*
import kotlin.math.log

class LookupActivity: AppCompatActivity() {


    companion object{
        const val Extra="Extras"
    }

    private val mockLookUpList = mutableListOf(
        LookUpData("DKI Jakarta",800,500,300),
        LookUpData("Sumatera Utara",120,80,30),
        LookUpData("Sumatera Selatan",50,60,85),
        LookUpData("Sumatera Barat",959,26,5),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lookup)
        title = "Lookup Activity"

        val lookUpAdapter = LookUpAdapter(mockLookUpList)

        rvLookUp.layoutManager =LinearLayoutManager(this)
        rvLookUp.adapter = lookUpAdapter

        backButton.setOnClickListener{
            onBackPressed()
        }

        ibDelInputButton.setOnClickListener {
            if(!etSearchInput.text.isEmpty()) { etSearchInput.text.clear() }
        }

        etSearchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("Search", "Before Text Changed")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("Search", "On Text Changed")
            }

            override fun afterTextChanged(p0: Editable?) {
                if(!p0.toString().isNullOrBlank()) {
                    var filteredLookUpData = filterLookUpData(mockLookUpList, p0.toString())
                    Log.i("Filtered Data", filteredLookUpData.toString())
                    mockLookUpList.clear()
                    mockLookUpList.addAll(filteredLookUpData as Collection<LookUpData>)
                    lookUpAdapter.notifyDataSetChanged()
                } else {

                }
            }
        })
    }

    private fun filterLookUpData(listOfLookUpData: MutableList<LookUpData>, keyword : String?) : MutableList<LookUpData>? {
        return mockLookUpList.filter { it.provincename.contains(keyword as CharSequence, true) } as? MutableList<LookUpData>
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