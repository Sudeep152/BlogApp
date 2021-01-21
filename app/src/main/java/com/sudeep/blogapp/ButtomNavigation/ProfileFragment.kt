package com.sudeep.blogapp.ButtomNavigation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sudeep.blogapp.Authentication.SingninActivity
import com.sudeep.blogapp.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoutBtn.setOnClickListener {
            logOut()
        }
        
    }
    fun logOut(){

        val logout=Intent(activity,SingninActivity::class.java)
        var sharedPreferences = this.activity?.getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
        var editor=sharedPreferences?.edit()
        Toast.makeText(activity,"Logout",Toast.LENGTH_LONG).show()
        editor?.putString("FLAG","FALSE")
        editor?.apply()
        startActivity(logout)
        this.activity?.finish()

    }




}