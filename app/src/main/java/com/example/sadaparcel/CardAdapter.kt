package com.example.sadaparcel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sadaparcel.databinding.CardCellBinding

class CardAdapter(private val items:List<Items>):RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from= LayoutInflater.from(parent.context)
        val binding = CardCellBinding.inflate(from,parent,false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int = items.size

}