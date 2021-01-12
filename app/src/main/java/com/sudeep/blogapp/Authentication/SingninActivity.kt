package com.sudeep.blogapp.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sudeep.blogapp.R
import kotlinx.android.synthetic.main.activity_singnin.*

class SingninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singnin)
        signtxt.setOnClickListener {
            val intent =Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}