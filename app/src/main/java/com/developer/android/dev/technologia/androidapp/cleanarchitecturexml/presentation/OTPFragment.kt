package com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.R
import com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.databinding.FragmentOTPBinding

class OTPFragment : DialogFragment(),IViewsHandling {

    private var binding: FragmentOTPBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOTPBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        setUpOTPInput()
        dialog?.setTitle("OTP Verification")
        return binding!!.root
    }

    private fun setUpOTPInput() {
        binding?.let { it->
            it.otpBox1.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0?.let { first->
                        if(first.length == 1){
                            it.otpBox2.requestFocus()
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })

            it.otpBox2.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                   p0?.let { second->
                       if(second.length==1){
                           it.otpBox3.requestFocus()
                       }
                   }
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })

            it.otpBox3.addTextChangedListener(object:TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                   p0?.let { third->
                       if(third.length==1){
                           it.otpBox4.requestFocus()
                       }
                   }
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })

            it.otpBox4.addTextChangedListener(object:TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0?.let { fourth->
                        if(fourth.length==1){
                            it.otpBox5.requestFocus()
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })

            it.otpBox5.addTextChangedListener(object:TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0?.let { fifth->
                        if(fifth.length==1){
                            it.otpBox6.requestFocus()
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })
            it.otpBox6.addTextChangedListener(object:TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0?.let { sixth->
                        if(sixth.length==1){
                            val otpValue = it.otpBox1.text.toString() + it.otpBox2.text.toString() +
                                    it.otpBox3.text.toString() + it.otpBox4.text.toString() +
                                    it.otpBox5.text.toString() + it.otpBox6.text.toString()

                            (activity as MainActivity).otpValue.value = otpValue
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })
        }
    }

    override fun dismissOtpDialogFragment() {
        dismiss()
    }
    override fun getUserId(): String {
        return ""
    }

}