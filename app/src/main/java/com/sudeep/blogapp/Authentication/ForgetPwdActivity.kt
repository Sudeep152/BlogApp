package com.sudeep.blogapp.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.sudeep.blogapp.R
import kotlinx.android.synthetic.main.activity_forget_pwd.*

class ForgetPwdActivity : AppCompatActivity() {
     lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        mAuth= FirebaseAuth.getInstance()
        fwd_pass.setOnClickListener {
            if (TextUtils.isEmpty(email_R.text.toString())){
                Toast.makeText(this,"Enter your mail",Toast.LENGTH_LONG).show()

            }else {
                Forgetpass()
            }
        }
    }
    fun Forgetpass(){
        mAuth.sendPasswordResetEmail(email_R.text.toString()).addOnCompleteListener {
            task->
            if (task.isSuccessful){
                Toast.makeText(this,"Check your mail", Toast.LENGTH_LONG).show()
                val intent=Intent(this,SingninActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,""+task.exception?.message.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}