package com.sudeep.blogapp.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.sudeep.blogapp.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth= FirebaseAuth.getInstance()

        logtxt.setOnClickListener {
            val intent=Intent(this,SingninActivity::class.java)
            startActivity(intent)
            finish()

        }

        r_Creat.setOnClickListener {
            if(TextUtils.isEmpty(email_R.text.toString())){
                Toast.makeText(this,"Enter email",Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(pass_R.text.toString()) ||TextUtils.isEmpty(pass_R2.text.toString())){
                Toast.makeText(this,"Enter password",Toast.LENGTH_LONG).show()
            }else if (!pass_R.text.contains("@") || pass_R.text.length<6){
                Toast.makeText(this,"password contains @  and 6 latter",Toast.LENGTH_LONG).show()
            }
            else if (pass_R.text.toString() != pass_R2.text.toString()){
                Toast.makeText(this,"Enter same password",Toast.LENGTH_LONG).show()
            }



            else{

                       singIn()
            }


        }
    }
    fun singIn(){
        mAuth.createUserWithEmailAndPassword(email_R.text.toString(),pass_R2.text.toString()).addOnCompleteListener(this){
            task->
            if (task.isSuccessful){
                Toast.makeText(this,"Your account created successfully",Toast.LENGTH_LONG).show()
                val intent=Intent(this,SingninActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,""+task.exception?.message.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }

}