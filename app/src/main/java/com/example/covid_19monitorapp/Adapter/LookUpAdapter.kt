package com.example.covid_19monitorapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19monitorapp.Data.LookUpData
import com.example.covid_19monitorapp.R

class LookUpAdapter(private val lookUpList: MutableList<LookUpData>) : RecyclerView.Adapter<LookUpViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LookUpViewHolder {
        return  LookUpViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_lookup, parent,false))
    }

    override fun onBindViewHolder(holder: LookUpViewHolder, position: Int) {
        holder.bind(lookUpList[position])
    }

    override fun getItemCount(): Int {
        return lookUpList.size
    }
}