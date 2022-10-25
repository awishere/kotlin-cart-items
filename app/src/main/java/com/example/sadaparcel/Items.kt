package com.example.sadaparcel

var itemList= mutableListOf<Items>()

class Items (

    var name: String,
    var price: Double,
    var stock: Int,
    var id: Int? = itemList.size

)