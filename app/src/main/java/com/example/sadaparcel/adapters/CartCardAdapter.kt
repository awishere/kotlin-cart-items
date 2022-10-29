package com.example.sadaparcel.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.TooltipCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sadaparcel.databinding.CartCardCellBinding
import com.example.sadaparcel.models.CartItems
import com.example.sadaparcel.models.Items

class CartCardAdapter(private val items: List<CartItems>, private val listener: CartInterface) :
    RecyclerView.Adapter<CartCardAdapter.CartCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartCardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CartCardCellBinding.inflate(from, parent, false)
        return CartCardViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CartCardViewHolder, position: Int) {
        holder.bindItems(items[position].cartItems, items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CartCardViewHolder(
        private val cardCellBinding: CartCardCellBinding,
        private var listener: CartInterface?
    ) : RecyclerView.ViewHolder(
        cardCellBinding.root
    ) {

        fun bindItems(items: Items, cartItems: CartItems) {
            cardCellBinding.name.text = items.name
            ("$" + items.price.toString()).also { cardCellBinding.price.text = it }
            (items.stock.toString() + " pcs").also { cardCellBinding.stock.text = it }
            cardCellBinding.quantity.text = cartItems.stock.toString()
            if (!items.disc) {
                cardCellBinding.discPrice.visibility = View.GONE
            }
            if (items.disc) {
                if (cartItems.stock > 1) {
                    cardCellBinding.discPrice.apply {
                        ("$" + (items.price / 2).toString()).also {
                            cardCellBinding.price.text = it
                        }
                        ("$" + items.price.toString()).also { cardCellBinding.discPrice.text = it }
                        cardCellBinding.discPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    }
                } else {
                    cardCellBinding.discPrice.visibility = View.GONE
                }

            }
            if (!items.disc) {
                cardCellBinding.discountBtn.visibility = View.GONE
            }
            if (items.disc) {
                cardCellBinding.discountBtn.setOnClickListener {
                    setToolTip(it, "Get 50% discount on getting more than 1 item")
                }
            }

            cardCellBinding.cartAddBtn.setOnClickListener {
                var quantity = cartItems.stock
                quantity += 1
                listener?.onAddBtnClick(cartItems, quantity)
            }
            cardCellBinding.cartDelBtn.setOnClickListener {
                var quantity = cartItems.stock
                quantity -= 1
                listener?.onAddBtnClick(cartItems, quantity)
            }

        }

        fun setOnClickListener(onClick: CartInterface) {
            this.listener = onClick
        }
        private fun setToolTip(view: View, text: String) {
            TooltipCompat.setTooltipText(view, text)
        }
    }

}

interface CartInterface {
    fun onAddBtnClick(cartItems: CartItems, stock: Int)
}