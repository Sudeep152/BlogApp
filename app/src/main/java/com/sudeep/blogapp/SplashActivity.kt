package com.sudeep.blogapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.postDelayed
import com.sudeep.blogapp.Authentication.SignupActivity

class SplashActivity : AppCompatActivity() {
   lateinit var handler :Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        handler=Handler()
        handler.postDelayed({

            var sharedPref=getSharedPreferences("user", MODE_PRIVATE)
            var check:String=sharedPref.getString("FLAG","FALSE")!!
            if (check=="TRUE"){
                val intent =Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
                finish()
            }
        },2000)
    }
}