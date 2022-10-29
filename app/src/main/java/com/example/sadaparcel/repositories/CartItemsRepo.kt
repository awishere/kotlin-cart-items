package com.example.sadaparcel.repositories

import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData
import com.example.sadaparcel.R
import com.example.sadaparcel.models.CartItems
import com.example.sadaparcel.models.Items


class CartRepo {
    companion object {
        private var instance: CartRepo? = null
        private val mutableCart: MutableLiveData<List<CartItems>> =
            MutableLiveData<List<CartItems>>()
        private val mutableTotalPrice: MutableLiveData<Double> = MutableLiveData<Double>()

        fun getInstance(): CartRepo {
            if (instance == null) {
                instance = CartRepo()
            }
            return instance as CartRepo
        }
    }


    fun getCart(): LiveData<List<CartItems>> {
        if (mutableCart.value == null) {
            initCart()
        }
        return mutableCart
    }

    private fun initCart() {
        mutableCart.value = ArrayList()
        calculateCartTotal()
    }

    fun addItemToCart(items: Items): Boolean {
        if (mutableCart.value == null) {
            initCart()
        }
        if (items.stock < 1) {
            return false
        }

        val cartItemList: MutableList<CartItems> = ArrayList(mutableCart.value!!)

        for (cartItem in cartItemList) {
            if (cartItem.cartItems.id == items.id) {
                println("Inside for")
                if (cartItem.stock == 12) {
                    return false
                }
                val index = cartItemList.indexOf(cartItem)
                cartItem.stock += 1
                cartItemList[index] = cartItem
                mutableCart.value = cartItemList
                calculateCartTotal()
                return true
            }
        }

        val cartItem = CartItems(items, 1)
        cartItemList.add(cartItem)
        mutableCart.value = cartItemList
        calculateCartTotal()
        return true
    }


    fun removeItemFromCart(cartItem: CartItems): Boolean {
        if (mutableCart.value == null) {
            initCart()
        }
        val cartItemList: MutableList<CartItems> = ArrayList(mutableCart.value!!)
        return if (cartItemList.contains(cartItem)) {
            cartItemList.remove(cartItem)
            mutableCart.value = cartItemList
            calculateCartTotal()
            true
        } else {
            false

        }
    }

    fun changeQuantity(cartItem: CartItems, quantity: Int): Boolean {
        if (mutableCart.value == null) {
            initCart()
        }
        if (cartItem.stock < 1) {
            return false
        }
        val cartItemList: MutableList<CartItems> = ArrayList(mutableCart.value!!)
        val updatedItems = CartItems(cartItem.cartItems, quantity)
        return if (cartItemList.contains(cartItem)) {
            cartItemList[cartItemList.indexOf(cartItem)] = updatedItems
            mutableCart.value = cartItemList
            calculateCartTotal()
            true
        } else {
            false
        }
    }

    private fun calculateCartTotal() {
        if (mutableCart.value == null) {
            return
        }
        var total = 0.0
        val cartItemList: List<CartItems> = ArrayList(mutableCart.value!!)
        for (cartItem: CartItems in cartItemList) {
            if (cartItem.cartItems.disc) {
                if (cartItem.stock == 1) {
                    total += cartItem.cartItems.price * cartItem.stock
                }
                //if the item is discounted and quantity raises to more than 1, add half of your item price for error reduction
                //since the discount is only applicable after the first item
                if (cartItem.stock > 1) {
                    total += cartItem.cartItems.price / 2
                    total += cartItem.cartItems.price / 2 * cartItem.stock
                }
            } else {
                total += cartItem.cartItems.price * cartItem.stock
            }
        }
        mutableCart.value = cartItemList
        mutableTotalPrice.value = total
    }


    fun totalPrice(): LiveData<Double> {
        if (mutableTotalPrice.value == null) {
            mutableTotalPrice.postValue(0.0)
        }
        return mutableTotalPrice
    }
}


