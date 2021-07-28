package com.example.tatvasoft_task

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter(var c: Context, var list: List<NumberModel>) :
    RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {
    var rowIndex: Int = -1;

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (!list.get(position).isClicked) {
            if (list.get(position).text.equals("0")) {
                holder.textView.setBackgroundColor(ContextCompat.getColor(c, R.color.red));
                holder.textView.isClickable = true
            } else {
                holder.textView.setBackgroundColor(ContextCompat.getColor(c, R.color.white));
                holder.textView.isClickable = false
            }
        }
        holder.textView.setOnClickListener {
            rowIndex == position
            list.get(position).text = "1"
            list.get(position).isClicked = true
            notifyDataSetChanged()
        }
        if (rowIndex == position) {
            if (list.get(position).text.equals("1")) {
                holder.textView.setBackgroundColor(ContextCompat.getColor(c, R.color.purple_200));
                holder.textView.isClickable = false
            }
        } else {
            if (list.get(position).text.equals("1")) {
                holder.textView.setBackgroundColor(ContextCompat.getColor(c, R.color.purple_200));
                holder.textView.isClickable = false
            } else {
                holder.textView.setBackgroundColor(ContextCompat.getColor(c, R.color.red));
            }
        }
    }
}