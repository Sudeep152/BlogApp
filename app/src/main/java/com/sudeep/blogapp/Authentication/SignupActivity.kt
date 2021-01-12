package com.sudeep.blogapp.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sudeep.blogapp.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        logtxt.setOnClickListener {
            val intent=Intent(this,SingninActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}