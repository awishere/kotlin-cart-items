package com.example.sadaparcel.repositories


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.sadaparcel.models.CartItems
import com.example.sadaparcel.models.Items
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)

class CartRepoTest{
 private  lateinit var cartRepo: CartRepo

    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()

     @Before
    fun setUp(){
     cartRepo = CartRepo()
     cartRepo = CartRepo.getInstance()
     cartRepo.getCart()
    }
    @Test
    fun cartIsNotEmpty(){
        val mutableCartList = cartRepo.getCart()
        assertThat(mutableCartList.value).isNotNull()
    }
    @Test
    fun getTotalWhenCartEmpty(){
        val emptyCartTotal = cartRepo.totalPrice()
        assertThat(emptyCartTotal.value).isEqualTo(0.0)
    }
    @Test
    fun isStockFinished(){
        val item = Items(
            "White Square",
            12.00,
            0,
            0
        )
        val addItemWhenEmpty = cartRepo.addItemToCart(item)
        assertThat(addItemWhenEmpty).isEqualTo(false)
    }
    @Test
    fun addItemInCartWhenItIsEmpty(){
        val item = Items(
            "White Square",
            12.00,
            12,
            0
        )
         val addItemWhenEmpty = cartRepo.addItemToCart(item)
         assertThat(addItemWhenEmpty).isEqualTo(true)
    }

    @Test
    fun removeItemInCartWhenNotExists(){
        val cartItem = CartItems(
            Items(
                "White Square",
                12.00,
                12,
                0
            ),
            12,
            )
        val removeCartItem = cartRepo.removeItemFromCart(cartItem)
        assertThat(removeCartItem).isEqualTo(false)
    }
    @Test
    fun updateItemInCartWhenNotExists(){
        val cartItem = CartItems(
            Items(
                "White Square",
                12.00,
                12,
                0
            ),
            12,
        )
        val updateCartItem = cartRepo.changeQuantity(cartItem,cartItem.stock)
        assertThat(updateCartItem).isEqualTo(false)
    }



}