package com.sankalp.srijantech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Handler().postDelayed({
            val nextPage = Intent(this, LoginPage::class.java)
            startActivity(nextPage)
        }, 3000)*/
      Handler(Looper.getMainLooper()).postDelayed({
       val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)
      },3000)
    }
}