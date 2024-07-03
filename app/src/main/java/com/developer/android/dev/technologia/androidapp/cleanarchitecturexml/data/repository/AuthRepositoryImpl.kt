package com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.data.repository

import com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.presentation.MainActivity
import com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.domain.model.User
import com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.domain.repository.AuthRepository
import com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.utils.Resource
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    var firebaseAuth: FirebaseAuth,
    var firebaseFireStore: FirebaseFirestore
) : AuthRepository {
    private val coroutineScope = CoroutineScope((Dispatchers.IO))
    override fun phoneNumberSignIn(
        phoneNumber: String,
        activity: MainActivity
    ): Flow<Resource<Boolean>> = channelFlow {
        try {
            trySend(Resource.Loading).isSuccess
            val options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phoneNumber)
                .setActivity(activity)
                .setTimeout(60, TimeUnit.SECONDS)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                        coroutineScope.launch {
                            signInWithAuthCredential(p0)
                        }
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        coroutineScope.launch {
                            trySend(
                                Resource.Error(p0.localizedMessage ?: "An Error Occurred")
                            ).isSuccess
                        }
                    }

                    override fun onCodeSent(
                        verificationId: String,
                        p1: PhoneAuthProvider.ForceResendingToken
                    ) {
                        coroutineScope.launch {
                            withContext(Dispatchers.Main){
                                // call here the OTP Enter view
                                activity.showOTPDialog()
                            }
                            activity.otpValue.collect{otp->
                                if(otp.isNotBlank()){
                                    trySend(
                                        (signInWithAuthCredential(
                                            PhoneAuthProvider.getCredential(verificationId,otp)
                                        ))
                                    ).isSuccess
                                }
                            }

                        }
                    }
                }).build()
            PhoneAuthProvider.verifyPhoneNumber(options)
            awaitClose()

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An Error Occurred")
        }
    }


    override fun isUserAuthenticated(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun getUserId(): String {
        return firebaseAuth.currentUser?.uid ?: ""
    }

//    override suspend fun signInWithAuthCredential(phoneAuthCredential: PhoneAuthCredential)
//    : Resource<Boolean> = suspendCoroutine { continuation->
//        firebaseAuth
//            .signInWithCredential(phoneAuthCredential)
//            .addOnSuccessListener {
//                continuation.resume(Resource.Success(true))
//            }
//            .addOnFailureListener { exception->
//                continuation.resume(
//                    Resource.Error(
//                        exception.localizedMessage?:"An Error Occurred"
//                    )
//                )
//            }
//    }
    /**Above code are work same but below code is more readable,we can code both**/
    override suspend fun signInWithAuthCredential(phoneAuthCredential: PhoneAuthCredential)
            : Resource<Boolean> {
        return try {
            firebaseAuth.signInWithCredential(phoneAuthCredential)
            Resource.Success(true)
        } catch (exception: Exception) {
            Resource.Error(exception.localizedMessage ?: "An Error Occurred")
        }
    }

    override fun createUserProfile(user: User): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }
}