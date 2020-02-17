package com.nike.urbandictionary.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.nike.urbandictionary.R
import com.nike.urbandictionary.app.models.DictionaryEntryModel

class DictionaryEntryRecyclerAdapter(
    val dictionaryEntries: List<DictionaryEntryModel>
) : RecyclerView.Adapter<DictionaryEntryRecyclerAdapter.DictionaryEntryViewHolder>() {

    inner class DictionaryEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = dictionaryEntries.size

    override fun onBindViewHolder(holder: DictionaryEntryViewHolder, position: Int) {
        holder.itemView.run {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryEntryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent) as ConstraintLayout

        return DictionaryEntryViewHolder(itemView)
    }
}