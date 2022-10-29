package com.example.sadaparcel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sadaparcel.models.CartItems
import com.example.sadaparcel.models.Items
import com.example.sadaparcel.repositories.CartRepo
import com.example.sadaparcel.repositories.HomeItemsRepo

class HomeVM() : ViewModel() {

    private  var itemMutableList: MutableLiveData<List<Items>> = MutableLiveData()
    private lateinit var cartMutableList: LiveData<List<CartItems>>
    private var homeItemsRepo: HomeItemsRepo? = null
    private var cartRepo: CartRepo? = null

    init {
        homeItemsRepo = HomeItemsRepo.getInstance()
        cartRepo = CartRepo.getInstance()
    }
    fun getItemsList(): MutableLiveData<List<Items>> {
        itemMutableList = homeItemsRepo?.getItems()!!
        return itemMutableList
    }
    fun getCartList(): LiveData<List<CartItems>>{
        cartMutableList = cartRepo?.getCart()!!
        return cartMutableList
    }
    fun addItemToCart( item:Items): Boolean? {
        return cartRepo?.addItemToCart(item)
    }
    fun removeItemFromCart(cartItems: CartItems): Boolean? {
        return cartRepo?.removeItemFromCart(cartItems)
    }
    fun changeQuantity(cartItems: CartItems,quantity:Int):Boolean?{
        return cartRepo?.changeQuantity(cartItems,quantity)
    }
    fun getTotalPrice(): LiveData<Double> {
        return cartRepo?.totalPrice()!!
    }

}