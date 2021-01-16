package com.sudeep.blogapp.ButtomNavigation

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sudeep.blogapp.PostCreate.CreatePostActivity
import com.sudeep.blogapp.R
import kotlinx.android.synthetic.main.activity_create_post.*
import kotlinx.android.synthetic.main.fragment_feed_.*

class Feed_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed_, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addPost.setOnClickListener {
            val intent=Intent(activity,CreatePostActivity::class.java)
            startActivity(intent)
        }


        }


    }
