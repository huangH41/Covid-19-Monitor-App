package com.example.covid_19monitorapp.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19monitorapp.Data.HotlineData
import com.example.covid_19monitorapp.HotlineFragment
import com.example.covid_19monitorapp.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_hotline.view.*
import kotlinx.android.synthetic.main.item_hotline.view.*
import kotlinx.android.synthetic.main.item_hotline.view.hotlineHeader
import kotlin.coroutines.coroutineContext

class HotlineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(data: HotlineData){
        if(data.imgIcon.isNotBlank()){
            Picasso.get().load(data.imgIcon).into(itemView.ivIconHotline)
        }
        itemView.hotlineHeader.text = data.name
        itemView.hotlineNumber.text = data.phone

        val context = itemView.context
        val mainActivity = MainActivity()

        itemView.phoneNumber.setOnClickListener {
            mainActivity.phoneCall(context, data.phone)
        }

    }

}