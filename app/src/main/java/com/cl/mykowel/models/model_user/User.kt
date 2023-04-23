package com.cl.mykowel.models.model_user

import com.google.gson.annotations.SerializedName

//
//data class UserAuthorization(val id: String?, val name: String?, val email: String?, val status: String?, val gender: String?)
//data class UserResponse(val code: Int?, val meta: String?, val data: User?)




class User {
    constructor(
        id: Int?,
        login: String?,
        name: String?,
        email: String?,
        phone: String?,
        password: String?,
        token: String?,
        isAdmin: String?
    ) {
        this.id = id
        this.login = login
        this.name = name
        this.email = email
        this.phone = phone
        this.password = password
        this.token = token
        this.isAdmin = isAdmin
    }

    constructor(login: String?, password: String?) {
        this.login = login
        this.password = password
    }

    constructor(login: String?, name: String?, email: String?, phone: String?, password: String?) {
        this.login = login
        this.name = name
        this.email = email
        this.phone = phone
        this.password = password
    }

    @SerializedName("id")

    var id: Int? = null

    @SerializedName("login")
    var login: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("password")
    var password: String? = null

    @SerializedName("token")
    var token: String? = null

    @SerializedName("is_admin")
    var isAdmin: String? = null


}