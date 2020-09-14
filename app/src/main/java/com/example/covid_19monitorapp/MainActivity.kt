package com.example.covid_19monitorapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_lookup.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    companion object{
        const val Extra="Extras"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("Activity","Activity:onCreate")
        lookUpArrIcon.setOnClickListener(){
            lookUpActivity()
        }
        hotlineArrIcon.setOnClickListener(){
            lookUp()
        }
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
    private fun lookUp(){
        val dataInput ="Lampung"
        val intent = Intent(this, LookupActivity::class.java).apply{
            action = Intent.ACTION_SEND
            data = Uri.parse("city:$dataInput")
        }
        startActivity(intent)
    }
    private fun hotlineDialog(){

    }
}