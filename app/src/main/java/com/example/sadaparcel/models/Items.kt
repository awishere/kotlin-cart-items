package com.example.sadaparcel.models
import com.example.sadaparcel.repositories.HomeItemsRepo
import com.example.sadaparcel.viewmodels.HomeVM


private var homeVM: HomeVM? = null


class Items (

    var name: String,
    var price: Double,
    var stock: Int,
    var id: Int? = homeVM?.getRes()?.value?.size

)