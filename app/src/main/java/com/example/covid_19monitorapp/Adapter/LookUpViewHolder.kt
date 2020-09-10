package com.example.covid_19monitorapp.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19monitorapp.LookUpData
import kotlinx.android.synthetic.main.item_lookup.view.*

class LookUpViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: LookUpData){
        itemView.headerList.text = data.provincename
        itemView.dataRecovery.text = "${data.numberofRecoveredCase}"
        itemView.dataPositif.text = "${data.numberofPositifCase}"
        itemView.dataDead.text = "${data.numberofDeathCase}"
    }
}