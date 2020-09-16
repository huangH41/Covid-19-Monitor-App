package com.example.covid_19monitorapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19monitorapp.Adapter.HotlineAdapter
import com.example.covid_19monitorapp.Data.HotlineData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_hotline.*
import kotlinx.android.synthetic.main.item_hotline.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.lang.Exception

class HotlineFragment: BottomSheetDialogFragment(){

    companion object{
        const val Extra="Extras"
        val okHttpClient = OkHttpClient()

        private val mockHotlineList = mutableListOf(
            HotlineData(name = "Loading..", imgIcon = "", phone="")
        )

        val hotlineAdapter = HotlineAdapter(mockHotlineList)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.dialog_hotline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hotlineClose.setOnClickListener(){
            dismiss()
        }

        rvHotline.layoutManager = LinearLayoutManager(activity)
        rvHotline.adapter = hotlineAdapter

        val request = Request.Builder().url("https://bncc-corona-versus.firebaseio.com/v1/hotlines.json")
            .build()

        okHttpClient
            .newCall(request)
            .enqueue(getCallback(hotlineAdapter))

    }

    private fun getCallback(hotlineAdapter: HotlineAdapter): Callback {
        return object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                activity?.runOnUiThread {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
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
                    activity?.runOnUiThread {
                        hotlineAdapter.updateData(hotlineListFromNetwork)
                    }
                }catch (e: Exception){
                    activity?.runOnUiThread {
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }
}