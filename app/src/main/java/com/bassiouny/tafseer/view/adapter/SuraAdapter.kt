package com.bassiouny.tafseer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bassiouny.tafseer.BR
import com.bassiouny.tafseer.R
import com.bassiouny.tafseer.databinding.ItemSuraBinding
import com.bassiouny.tafseer.model.model.Sura
import com.bassiouny.tafseer.supported_file.AdapterClick
import kotlinx.android.synthetic.main.item_sura.view.*

class SuraAdapter (var suraList: List<Sura>, var iAdapter:AdapterClick<Sura>,var ctx:Context): RecyclerView.Adapter<SuraAdapter.ViewHolder>() {



    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemSuraBinding: ItemSuraBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.item_sura, parent, false);

        //val v = LayoutInflater.from(parent.context).inflate(R.layout.item_sura, parent, false)

        return ViewHolder(itemSuraBinding);
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(suraList[position])

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return suraList.size
    }

    fun setList(suraList: List<Sura>){
        this.suraList = suraList
        notifyDataSetChanged()
    }

    //the class is hodling the list view
    inner class ViewHolder(val binding: ItemSuraBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: Sura) {
            binding.setVariable(BR.sura, item)
            binding.executePendingBindings()



//            itemView.tvNumberOfAya.text = "${item.count}"
//            itemView.tvNumber.text = item.index
//            if (item.place.equals("Mecca",false))
//                itemView.ivSuraType.setImageDrawable(ctx.getDrawable(R.drawable.maka))
//            else
//                itemView.ivSuraType.setImageDrawable(ctx.getDrawable(R.drawable.madina))
//
            itemView.setOnClickListener {
                iAdapter.onClick(item,adapterPosition)
            }
        }
    }

}