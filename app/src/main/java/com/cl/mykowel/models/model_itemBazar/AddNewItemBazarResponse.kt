package com.cl.mykowel.models.model_itemBazar

data class AddNewItemBazarResponse(
    var id: Int,
    var title: String,
    var description: String,
    var price: String,
    var category: String,
    var pnumber: String,
    var user_id: Int,
    var photo: String,
    var checked: Int,
    var sold: Int,
)
