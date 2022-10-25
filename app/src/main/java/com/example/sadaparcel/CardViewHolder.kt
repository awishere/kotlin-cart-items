package com.example.sadaparcel

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.sadaparcel.databinding.CardCellBinding

class CardViewHolder(private val cardCellBinding:CardCellBinding):RecyclerView.ViewHolder(cardCellBinding.root
) {
  @SuppressLint("SetTextI18n")
  fun bindItems(items: Items){

      cardCellBinding.name.text= items.name
      cardCellBinding.price.text = "$" + items.price.toString()
      cardCellBinding.stock.text = items.stock.toString()


  }
}