package com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.domain.repository

import com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.presentation.MainActivity
import com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.domain.model.User
import com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.utils.Resource
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun phoneNumberSignIn(phoneNumber:String,activity: MainActivity): Flow<Resource<Boolean>>

    fun isUserAuthenticated():Boolean

    fun getUserId():String

    suspend fun signInWithAuthCredential(phoneAuthCredential: PhoneAuthCredential):Resource<Boolean>

    fun createUserProfile(user: User):Flow<Resource<Boolean>>
}