package com.example.covid_19monitorapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lookup.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.lookUpArrIcon

class LookupActivity: AppCompatActivity() {
    companion object{
        const val Extra="Extra"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lookup)

        backButton.setOnClickListener(){
            homeActivity()
        }

    }
    private fun homeActivity(){
        val intent = Intent(this, MainActivity::class.java).apply{
            putExtra(Extra,"Halo")
        }
        startActivity(intent)
    }
}