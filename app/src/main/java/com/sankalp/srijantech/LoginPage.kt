package com.sankalp.srijantech

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sankalp.srijantech.databinding.LoginPageActivityBinding

class LoginPage : AppCompatActivity() {

    private lateinit var binding: LoginPageActivityBinding
    private var isCredentialsOK = false

    private var loginViewModel:  LoginViewModel? = null
    private var username: String? = null
    private var password: String? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.login_page_activity)
        val loginViewModelFactory = LoginViewModelFactory(this)
        loginViewModel = ViewModelProvider(this, loginViewModelFactory). get(LoginViewModel::class.java)


        binding.edUserName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, textCount: Int) {
                    username  = text.toString()
                    if (username?.length!! > 0){
                        loginViewModel?.checkUsername(text.toString())
                    } else{
                        binding.userNameStatus.visibility = View.VISIBLE
                        binding.loginButton.isEnabled = false
                    }
              }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.edPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int,  textCount: Int) {
                password = text.toString()
                if(password?.length!! > 5){
                    loginViewModel?.checkPassword(text.toString())
                }else{
                    binding.passwordStatus.visibility = View.VISIBLE
                    binding.loginButton.isEnabled = false
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

            binding.loginButton.setOnClickListener {
                val intent = Intent(this, UserWelcomePage::class.java)
                intent.putExtra(Intent.EXTRA_TEXT,username)
                startActivity(intent)
                finish()
            }

            loginViewModel?.isUsernameOK?.observe(this){
                if (it){
                    binding.userNameStatus.visibility = View.GONE
                    if (loginViewModel?.isPasswordValid()== true){
                        binding.passwordStatus.visibility = View.GONE
                        binding.loginButton.isEnabled = true
                    } else{
                        binding.passwordStatus.visibility = View.VISIBLE
                    }
                } else{
                    binding.userNameStatus.visibility = View.VISIBLE
                }
            }
        loginViewModel?.isPasswordOK?.observe(this){
            if(it){
                binding.passwordStatus.visibility = View.GONE
                if(loginViewModel?.isUsernameValid() == true){
                    binding.userNameStatus.visibility = View.GONE
                    binding.loginButton.isEnabled = true
                }else{
                    binding.userNameStatus.visibility = View.VISIBLE

                }
            }else{
                binding.passwordStatus.visibility = View.VISIBLE
            }
        }
        }
    }
