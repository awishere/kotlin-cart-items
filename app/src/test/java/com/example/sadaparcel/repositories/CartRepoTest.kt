package com.example.sadaparcel.repositories


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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

    }
    @Test
    fun cartIsNotEmpty(){
        val mutableCartList = cartRepo.getCart()
        assertThat(mutableCartList).isNotNull()
    }
    @Test
    fun isStockFinished(){
        val item = Items(
            "White Square",
            12.00,
            0,
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
        )
         val addItemWhenEmpty = cartRepo.addItemToCart(item)
         assertThat(addItemWhenEmpty).isEqualTo(true)
    }
}