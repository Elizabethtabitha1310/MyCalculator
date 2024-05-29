package com.example.mycalculator.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycalculator.R

class HistoryAdapter(private var historyList: List<HistoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtExpression: TextView = view.findViewById(R.id.txtExpression)
        val txtResult: TextView = view.findViewById(R.id.txtResult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.txtExpression.text = item.expression
        holder.txtResult.text = item.result
    }

    override fun getItemCount(): Int = historyList.size

    fun updateHistory(newHistoryList: List<HistoryItem>) {
        historyList = newHistoryList
        notifyDataSetChanged()
    }
}
