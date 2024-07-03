package com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var otpValue = MutableStateFlow<String>("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(checkAuthState()){

        }else{
            binding.userAuthenticationLayout.visibility = View.VISIBLE
            binding.appLogo.visibility = View.VISIBLE
            binding.userNumberLayout.visibility = View.VISIBLE
            binding.textInputLayout1.visibility = View.VISIBLE
            binding.etNumber.visibility = View.VISIBLE
            binding.btProceed.visibility = View.VISIBLE
        }

    }

    fun showOTPDialog(){
        val otpFragment = OTPFragment()
        otpFragment.show(supportFragmentManager,"OTPDialogFragment")
    }
    private fun checkAuthState(): Boolean {
        return false
    }
}