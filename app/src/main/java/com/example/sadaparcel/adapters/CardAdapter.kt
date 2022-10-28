package com.example.sadaparcel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sadaparcel.databinding.CardCellBinding
import com.example.sadaparcel.models.Items



class CardAdapter(private val items: List<Items>,private val  listener: ItemInterface) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {

        val from = LayoutInflater.from(parent.context)
        val binding = CardCellBinding.inflate(from, parent, false)
        return CardViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindItems(items[position],items)

    }

    override fun getItemCount(): Int = items.size


    inner class CardViewHolder(private val cardCellBinding: CardCellBinding, private var listener:ItemInterface?) :
        RecyclerView.ViewHolder(cardCellBinding.root) {


        fun bindItems(items: Items, listItems: List<Items>) {
            cardCellBinding.name.text = items.name
            cardCellBinding.price.text = "$" + items.price.toString()
            cardCellBinding.stock.text = items.stock.toString() + " pcs"
            cardCellBinding.addBtn.setOnClickListener{
                val position = adapterPosition

                listener?.addItem(listItems[position])
            }

        }
        fun setOnClickListener(onClick: ItemInterface) {
            this.listener = onClick
        }
    }
    }





interface ItemInterface {
    fun addItem(items: Items)
}
