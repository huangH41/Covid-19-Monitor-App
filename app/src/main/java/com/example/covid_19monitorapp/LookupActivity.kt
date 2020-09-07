package com.example.covid_19monitorapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lookup.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.lookUpArrIcon

class LookupActivity: AppCompatActivity() {
    companion object{
        const val Extra="Extras"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lookup)
        title= "Lookup Activity"

        val value = intent.getStringExtra(Extra)
        search.setQuery(value,false)

        backButton.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
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