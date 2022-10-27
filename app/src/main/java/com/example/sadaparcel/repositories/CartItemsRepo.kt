package com.example.sadaparcel.repositories

import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData
import com.example.sadaparcel.models.CartItems
import com.example.sadaparcel.models.Items


class CartRepo {
    companion object{
        private var instance: CartRepo? = null
        private val mutableCart: MutableLiveData<List<CartItems>> = MutableLiveData<List<CartItems>>()
        private val mutableTotalPrice: MutableLiveData<Double?> = MutableLiveData<Double?>()

        fun getInstance(): CartRepo {
            if (instance == null){
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
        mutableCart.postValue(ArrayList<CartItems>())
    }

    fun addItemToCart(items: Items): Boolean {
        if (mutableCart.value == null) {
            initCart()
        }
        if(items.stock < 1){
            return false
        }
        val cartItemList: MutableList<CartItems> = ArrayList(mutableCart.value!!)
        for (cartItem in cartItemList) {
            if (cartItem.cartItems.id?.equals(items.id)!!) {
                if (cartItem.stock == 12) {
                    return false
                }
                val index = cartItemList.indexOf(cartItem)
                cartItem.stock += 1
                cartItemList[index] = cartItem
                mutableCart.postValue(cartItemList)
                return true

            }
        }
        val cartItem: CartItems = CartItems(items,1)
        cartItemList.add(cartItem)
        println(cartItemList)
        mutableCart.postValue(cartItemList)
        return true
    }


        fun removeItemFromCart(cartItem: CartItems) {}

        fun changeQuantity(cartItem: CartItems, quantity: Int) {}

        private fun calculateCartTotal() {}


        fun totalPrice(): LiveData<Double?> {
            return mutableTotalPrice
        }
}


