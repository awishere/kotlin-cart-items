package com.example.sadaparcel.repositories

import androidx.lifecycle.LiveData
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
        )
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        itemMutableList.postValue(itemList)
    }
}