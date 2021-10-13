package com.sankalp.srijantech

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class LoginViewModel(context: Context): ViewModel() {

    private var _isCredentialsOK = MutableLiveData<Boolean>()
    val isCredentialsOK : LiveData<Boolean> = _isCredentialsOK

    private var _isUsernameOK = MutableLiveData<Boolean>()
    val isUsernameOK : LiveData<Boolean> = _isUsernameOK

    private var _isPasswordOK = MutableLiveData<Boolean>()
    val isPasswordOK : LiveData<Boolean> = _isPasswordOK

    fun checkUsername(username: String){
        _isUsernameOK.value = username.isNotEmpty() && username.isNotBlank()
    }
    fun checkPassword(password: String){
       _isPasswordOK.value = password.length > 5 && password.isNotBlank() && password.isNotEmpty()
    }
    fun isUsernameValid(): Boolean?{
         return _isUsernameOK.value
    }
    fun isPasswordValid(): Boolean?{
        return _isPasswordOK.value
    }
}
  class LoginViewModelFactory(var context: Context): ViewModelProvider.Factory{
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
                return LoginViewModel(context = context) as T
            }
            throw IllegalArgumentException("Unkown Class")
      }
  }