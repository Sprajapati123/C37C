package com.example.c37c.repository

import com.example.c37c.model.UserModel
import com.google.firebase.auth.FirebaseUser
import javax.security.auth.callback.Callback

interface UserRepo {
    //{
    //    "success":true,
    //    "message":"Email snet"
    // }
    fun login(email: String, password: String,
              callback: (Boolean, String)-> Unit)

    fun forgetPassword(email:String,callback: (Boolean, String) -> Unit)


    fun register(
        email: String, password: String,
        callback: (Boolean, String, String) -> Unit
    )

    fun addUserToDatabase(userId: String, model: UserModel,
                          callback: (Boolean, String) -> Unit
    )

    fun getUserById(userId: String, callback: (Boolean, UserModel?) -> Unit)

    fun getAllUser(callback: (Boolean, List<UserModel>?) -> Unit)

    fun getCurrentUser(): FirebaseUser?

    fun deleteUser(userId: String,callback: (Boolean, String) -> Unit)

    fun updateProfile(userId: String, model: UserModel,
                      callback: (Boolean, String) -> Unit)
}