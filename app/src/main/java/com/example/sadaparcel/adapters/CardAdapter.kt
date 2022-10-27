package com.example.sadaparcel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sadaparcel.databinding.CardCellBinding
import com.example.sadaparcel.models.Items

class CardAdapter(private val items:List<Items>):RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from= LayoutInflater.from(parent.context)
        val binding = CardCellBinding.inflate(from,parent,false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindItems(items[position])
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = items.size

}
class CardViewHolder(private val cardCellBinding:CardCellBinding):RecyclerView.ViewHolder(cardCellBinding.root
) {


    fun bindItems(items: Items){

        cardCellBinding.name.text= items.name
        cardCellBinding.price.text = "$" + items.price.toString()
        cardCellBinding.stock.text = items.stock.toString() + " pcs"




    }
}