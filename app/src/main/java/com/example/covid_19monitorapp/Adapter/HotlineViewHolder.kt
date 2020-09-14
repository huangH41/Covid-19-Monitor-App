package com.example.covid_19monitorapp.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19monitorapp.HotlineData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hotline.view.*

class HotlineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: HotlineData){
        if(data.imgIcon.isNotBlank()){
            Picasso.get().load(data.imgIcon).into(itemView.ivIconHotline)
        }
        itemView.hotlineHeader.text = data.name
        itemView.hotlineNumber.text = data.phone
    }
}