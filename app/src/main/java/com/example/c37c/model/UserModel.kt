package com.example.c37c.model

data class UserModel(
    val userId : String = "",
    val email : String = "",
    val firstName : String = "",
    val lastName : String = "",
    val contact : String = "",
    val dob : String = "",
){
    fun toMap() : Map<String,Any?>{
        return mapOf(
            "contact" to contact
        )
    }
}
