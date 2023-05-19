package com.cl.mykowel.models.model_user


// The class UserDataModel is used for the response from the server when we register or log into an account.

data class UserDataModel(
    val id: Int?,
    val login: String?,
    val name: String?,
    val email: String?,
    val phone: String?,
    val password: String?,
    val token: String?,
    val isAdmin: String?
)

//The class UserAuthModel is used for logging into an account. It only stores the password and login information.

data class UserAuthModel(
    val login: String?,
    val password: String?
)

//The class UserRegModel is used for registration. It stores the registration data that will be sent to the server, and the response will be received in the UserDataModel.

data class UserRegModel(
    val login: String?,
    val name: String?,
    val email: String?,
    val phone: String?,
    val password: String?
)
//
//class User {
//    constructor(
//        id: Int?,
//        login: String?,
//        name: String?,
//        email: String?,
//        phone: String?,
//        password: String?,
//        token: String?,
//        isAdmin: String?
//    ) {
//        this.id = id
//        this.login = login
//        this.name = name
//        this.email = email
//        this.phone = phone
//        this.password = password
//        this.token = token
//        this.isAdmin = isAdmin
//    }
//
//    constructor(login: String?, password: String?) {
//        this.login = login
//        this.password = password
//    }
//
//    constructor(login: String?, name: String?, email: String?, phone: String?, password: String?) {
//        this.login = login
//        this.name = name
//        this.email = email
//        this.phone = phone
//        this.password = password
//    }
//
//    @SerializedName("id")
//
//    var id: Int? = null
//
//    @SerializedName("login")
//    var login: String? = null
//
//    @SerializedName("name")
//    var name: String? = null
//
//    @SerializedName("email")
//    var email: String? = null
//
//    @SerializedName("phone")
//    var phone: String? = null
//
//    @SerializedName("password")
//    var password: String? = null
//
//    @SerializedName("token")
//    var token: String? = null
//
//    @SerializedName("is_admin")
//    var isAdmin: String? = null
//
//
//}