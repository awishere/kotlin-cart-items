package com.example.sadaparcel.repositories

import androidx.lifecycle.MutableLiveData
import com.example.sadaparcel.models.Items


class HomeItemsRepo {

    companion object{
        private var instance: HomeItemsRepo? = null
        private  var itemMutableList: MutableLiveData<List<Items>> = MutableLiveData()

        fun getInstance(): HomeItemsRepo {
            if (instance == null){
                instance = HomeItemsRepo()
            }
            return instance as HomeItemsRepo
        }
    }
    fun  getItems():  MutableLiveData<List<Items>> {
        itemMutableList = MutableLiveData()
        loadItems()
        return itemMutableList;
    }

    private fun loadItems() {
        val itemList = mutableListOf<Items>()
        val item = Items(
            "White Square",
            12.00,
            12,
            0
        )
        val item1 = Items(
            "Black Square",
            10.00,
            12,
            1
        )
        val item2 = Items(
            "Pink Square",
            14.00,
            12,
            2,
            true
        )
        val item3 = Items(
            "Red Square",
            19.00,
            12,
            3
        )
        val item4 = Items(
            "Purple Square",
            11.00,
            12,
            4
        )
        val item5 = Items(
            "White Square",
            15.00,
            12,
            5
        )
        itemList.add(item)
        itemList.add(item1)
        itemList.add(item2)
        itemList.add(item3)
        itemList.add(item4)
        itemList.add(item5)

        itemMutableList.postValue(itemList)
    }
}