package com.developer.android.dev.technologia.androidapp.cleanarchitecturexml.presentation

interface IViewsHandling {
    fun hideProgressBar(){}
    fun showProgressBar(){}
    fun showError(error:String){}
    fun changeViewsVisibility(){}
    fun showHomePage(){}
    fun dismissOtpDialogFragment(){}
    fun getUserId():String
}