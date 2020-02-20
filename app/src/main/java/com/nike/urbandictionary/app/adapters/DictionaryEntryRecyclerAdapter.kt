package com.nike.urbandictionary.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.nike.urbandictionary.R
import com.nike.urbandictionary.app.models.DictionaryEntryModel
import kotlinx.android.synthetic.main.list_item.view.*

class DictionaryEntryRecyclerAdapter(
    private val dictionaryEntries: MutableLiveData<List<DictionaryEntryModel>>
) : RecyclerView.Adapter<DictionaryEntryRecyclerAdapter.DictionaryEntryViewHolder>() {

    inner class DictionaryEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = dictionaryEntries.value?.size ?: 0

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onBindViewHolder(holder: DictionaryEntryViewHolder, position: Int) {
        holder.itemView.run {
            dictionaryEntries.value?.get(position)?.let { entry ->
                word.text = entry.searchWord
                definition.text = entry.definition
                examples.text = entry.examples
                likes.text = entry.thumbsUp.toString()
                dislikes.text = entry.thumbsDown.toString()
                author_date.text = entry.authorLine
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryEntryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false) as ConstraintLayout

        return DictionaryEntryViewHolder(itemView)
    }
}