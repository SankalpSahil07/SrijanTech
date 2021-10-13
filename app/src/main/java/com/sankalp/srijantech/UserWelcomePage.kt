package com.sankalp.srijantech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBinderMapper
import androidx.databinding.DataBindingUtil
import com.sankalp.srijantech.databinding.UserWelcomePageActivityBinding

class UserWelcomePage : AppCompatActivity() {

        private lateinit var binding: UserWelcomePageActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.user_welcome_page_activity)

        val username = intent.getStringExtra(Intent.EXTRA_TEXT)

        binding.idUsername.text = "Hello, $username"
    }
}