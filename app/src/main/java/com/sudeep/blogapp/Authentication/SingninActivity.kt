package com.sudeep.blogapp.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.sudeep.blogapp.MainActivity
import com.sudeep.blogapp.R
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_singnin.*
import kotlinx.android.synthetic.main.activity_singnin.email_R
import kotlinx.android.synthetic.main.activity_singnin.pass_R
import kotlinx.android.synthetic.main.activity_singnin.r_Creat

class SingninActivity : AppCompatActivity() {
    lateinit var  mAuth:FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singnin)
        signtxt.setOnClickListener {
            val intent =Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

  mAuth= FirebaseAuth.getInstance()
        fwd_text.setOnClickListener { val intent=Intent(this,ForgetPwdActivity::class.java)
        startActivity(intent)}

        r_Creat.setOnClickListener {

            if(TextUtils.isEmpty(email_R.text.toString())){
                Toast.makeText(this,"Enter email", Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(pass_R.text.toString())){
                Toast.makeText(this,"Enter password", Toast.LENGTH_LONG).show()
            }else{

                logIn()
            }

        }
        
    }
    fun logIn(){
        mAuth.signInWithEmailAndPassword(email_R.text.toString(),pass_R.text.toString()).addOnCompleteListener {
            task->
            if (task.isSuccessful){
                Toast.makeText(this,"Welcome",Toast.LENGTH_LONG).show()
                var intent =Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,""+task.exception?.message.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }
}