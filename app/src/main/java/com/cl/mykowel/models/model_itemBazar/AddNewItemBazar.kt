package com.cl.mykowel.models.model_itemBazar

//The class needed for creating a new listing in the marketplace is AdListingModel. It stores information such as the title, description, price, category, and path to the photo.
data class AddNewItemBazar(
    var title: String,
    var description: String,
    var price: String,
    var category: String,
    var photo: String,
)
