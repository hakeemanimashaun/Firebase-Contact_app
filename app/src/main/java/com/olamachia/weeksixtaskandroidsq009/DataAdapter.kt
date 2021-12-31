package com.olamachia.weeksixtaskandroidsq009

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(
    private var list: ArrayList<DatabaseModel>,
    private var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tv_name)
        var number: TextView = itemView.findViewById(R.id.tv_contact)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.recycler_view_contact, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text = list[position].name
        holder.number.text = list[position].number

        holder.itemView.setOnClickListener {
            itemClickListener.onViewClicked(position, list[position])
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

}

