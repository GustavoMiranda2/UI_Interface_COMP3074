package com.example.uiprototypebeta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(
    private val titles: Array<String>,
    private val snippets: Array<String>
) : RecyclerView.Adapter<PostAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.tvPostTitle)
        val snippet: TextView = v.findViewById(R.id.tvPostSnippet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.title.text = titles[position]
        holder.snippet.text = snippets[position]
    }

    override fun getItemCount(): Int = titles.size
}
