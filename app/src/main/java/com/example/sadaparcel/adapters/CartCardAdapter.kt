package com.example.sadaparcel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sadaparcel.databinding.CartCardCellBinding
import com.example.sadaparcel.models.CartItems
import com.example.sadaparcel.models.Items

class CartCardAdapter(private val items:List<CartItems>):RecyclerView.Adapter<CartCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartCardViewHolder {
        val from= LayoutInflater.from(parent.context)
        val binding = CartCardCellBinding.inflate(from,parent,false)
        return CartCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartCardViewHolder, position: Int) {
        holder.bindItems(items[position].cartItems,items[position])
    }
    override fun getItemCount(): Int = items.size

}
class CartCardViewHolder(private val cardCellBinding:CartCardCellBinding):RecyclerView.ViewHolder(cardCellBinding.root
) {

    fun bindItems(items: Items,cartItems: CartItems){
        cardCellBinding.name.text= items.name
        cardCellBinding.price.text = "$" + items.price.toString()
        cardCellBinding.stock.text = items.stock.toString() + " pcs"
        cardCellBinding.quantity.text = cartItems.stock.toString()

    }
}