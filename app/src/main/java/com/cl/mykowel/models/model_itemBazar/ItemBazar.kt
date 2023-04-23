package com.cl.mykowel.models

import android.content.Intent

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
