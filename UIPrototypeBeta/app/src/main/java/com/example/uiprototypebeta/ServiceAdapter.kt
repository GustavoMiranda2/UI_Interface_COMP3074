package com.example.uiprototypebeta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ServiceAdapter(
    private val items: List<ServiceItem>,
    private val listener: OnServiceClick
) : RecyclerView.Adapter<ServiceAdapter.VH>() {

    interface OnServiceClick { fun onServiceClick(item: ServiceItem) }

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.tvTitle)
        val subtitle: TextView = v.findViewById(R.id.tvSubtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val it = items[position]
        holder.title.text = it.title
        holder.subtitle.text = it.subtitle
        holder.itemView.setOnClickListener { _ -> listener.onServiceClick(it) }
    }

    override fun getItemCount(): Int = items.size
}
