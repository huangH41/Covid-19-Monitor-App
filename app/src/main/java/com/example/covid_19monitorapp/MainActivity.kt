package com.example.covid_19monitorapp

import android.content.Context
import android.content.Intent
import android.icu.text.IDNA
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_hotline.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val Extra="Extras"
        val hotlineFragment = HotlineFragment()
        val infoFragment = InfoFragment()
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