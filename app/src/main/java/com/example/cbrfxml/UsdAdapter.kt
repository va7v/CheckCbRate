package com.example.cbrfxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cbrfxml.R
import androidx.appcompat.app.AppCompatActivity
import com.example.cbrfxml.data.Usd

class UsdAdapter : RecyclerView.Adapter<UsdViewHolder>() {

    private val items = mutableListOf<Usd>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsdViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, null)
        return UsdViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UsdViewHolder, position: Int) {
        val date = items[position].date
        val value = items[position].value
        holder.bind(date, value)
    }

    fun addItems(newItems: List<Usd>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class UsdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val dateTV = itemView.findViewById<TextView>(R.id.date_text_view)
    private val valueTV = itemView.findViewById<TextView>(R.id.value_text_view)

    fun bind(title: String, value: String) {
        dateTV.text = title
        valueTV.text = value
    }
}