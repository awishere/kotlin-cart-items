package com.example.sadaparcel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.TooltipCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sadaparcel.databinding.CardCellBinding
import com.example.sadaparcel.models.Items
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CardAdapter(private val items: List<Items>, private val listener: ItemInterface) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {

        val from = LayoutInflater.from(parent.context)
        val binding = CardCellBinding.inflate(from, parent, false)
        return CardViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindItems(items[position], items)

    }

    override fun getItemCount(): Int = items.size


    inner class CardViewHolder(
        private val cardCellBinding: CardCellBinding,
        private var listener: ItemInterface?
    ) :
        RecyclerView.ViewHolder(cardCellBinding.root) {


        fun bindItems(items: Items, listItems: List<Items>) {
            cardCellBinding.name.text = items.name
            ("$" + items.price.toString()).also { cardCellBinding.price.text = it }
            (items.stock.toString() + " pcs").also { cardCellBinding.stock.text = it }
            if (!items.disc) {
                cardCellBinding.discountBtn.visibility = View.GONE
            }
            if (items.disc) {
                cardCellBinding.discountBtn.setOnClickListener {
                    setToolTip(it, "Get 50% discount on getting more than 1 item")
                }
            }
            cardCellBinding.addBtn.setOnClickListener {
                val position = adapterPosition
                listener?.onClick(listItems[position])
            }

        }

        fun setOnClickListener(onClick: ItemInterface) {
            this.listener = onClick
        }

        private fun setToolTip(view: View, text: String) {
            TooltipCompat.setTooltipText(view, text)
        }
    }
}


interface ItemInterface {
    fun onClick(items: Items)
}
