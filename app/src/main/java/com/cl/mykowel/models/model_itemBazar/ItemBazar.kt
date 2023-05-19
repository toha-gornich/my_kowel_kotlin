package com.cl.mykowel.models.model_itemBazar

//The class that serves for the response from the server in the Marketplace tab or in the profile, in the list of items that the user puts up for sale.

data class ItemBazar(
    var id: Int,
    var title: String,
    var description: String,
    var price: String,
    var category: String,
    var photo: String,
    var checked: Int,
    var pnumber: String
)
