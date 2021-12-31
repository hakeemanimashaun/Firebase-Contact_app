package com.olamachia.weeksixtaskandroidsq009

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CellContactAdapter( items: List<CellContactModel>,   context: Context)
    : RecyclerView.Adapter<CellContactAdapter.ViewHolder>() {
    private var list : List<CellContactModel> = items
    private var context: Context = context


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun createView(cellContactModel: CellContactModel){
            itemView.findViewById<TextView>(R.id.tv_name).text = cellContactModel.name
            itemView.findViewById<TextView>(R.id.tv_number).text = cellContactModel.number
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellContactAdapter.ViewHolder {
        val v = LayoutInflater.from(context)
            .inflate(R.layout.contact_card_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CellContactAdapter.ViewHolder, position: Int) {
        holder.createView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}