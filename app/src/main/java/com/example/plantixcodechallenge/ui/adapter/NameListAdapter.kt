package com.example.plantixcodechallenge.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.plantixcodechallenge.R
import com.example.plantixcodechallenge.data.model.Name
import com.example.plantixcodechallenge.databinding.ItemRowBinding

class NameListAdapter(private var modelList: List<Name> = ArrayList()) :
    RecyclerView.Adapter<NameListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_row,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name: Name = modelList[position]
        holder.itemRowBinding.model = name
        holder.bind(name)
    }

    override fun getItemCount(): Int = modelList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Name>) {
        modelList = list
        notifyDataSetChanged()
    }

    class ViewHolder(var itemRowBinding: ItemRowBinding) :
        RecyclerView.ViewHolder(itemRowBinding.root) {

        fun bind(name: Name) {
            itemRowBinding.setVariable(BR.model, name)
            itemRowBinding.executePendingBindings()
        }
    }
}
