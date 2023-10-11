package com.example.day23catshandbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val dataSet: Array<String>, private val onClick: (Int) -> Unit) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(view: View, val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        private var currentItemPosition: Int? = null
        private val textView: TextView = view.findViewById(R.id.textView)
        init {
            view.setOnClickListener {
                currentItemPosition?.let {
                    onClick(it)
                }
            }
        }

        fun bind(data: String, position: Int) {
            currentItemPosition = position
            textView.text = data
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position], position)
    }

    override fun getItemCount(): Int = dataSet.size
}
