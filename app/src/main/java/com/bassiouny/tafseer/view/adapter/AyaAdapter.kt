package com.bassiouny.tafseer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bassiouny.tafseer.R
import com.bassiouny.tafseer.model.model.Aya
import com.bassiouny.tafseer.supported_file.AdapterClick
import kotlinx.android.synthetic.main.item_aya.view.*

class AyaAdapter(val ayaList: List<Aya>,val iAdapter:AdapterClick<Aya>): RecyclerView.Adapter<AyaAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyaAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_aya, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: AyaAdapter.ViewHolder, position: Int) {
        holder.bindItems(ayaList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return ayaList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: Aya) {
            itemView.tvNumber.text = "${adapterPosition+1}"
            itemView.tvAyaName.text = item.text

            itemView.setOnClickListener {
                iAdapter.onClick(item,adapterPosition)
            }
        }
    }
}