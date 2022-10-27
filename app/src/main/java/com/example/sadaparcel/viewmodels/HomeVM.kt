package com.example.sadaparcel.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sadaparcel.models.Items
import com.example.sadaparcel.repositories.HomeItemsRepo

class HomeVM() : ViewModel() {


    private  var itemMutableList: MutableLiveData<List<Items>> = MutableLiveData()
    private var homeItemsRepo: HomeItemsRepo? = null

    init {
        homeItemsRepo = HomeItemsRepo.getInstance()
        itemMutableList = homeItemsRepo?.getItems()!!
    }


    fun getRes(): MutableLiveData<List<Items>> {
        return itemMutableList
    }
}