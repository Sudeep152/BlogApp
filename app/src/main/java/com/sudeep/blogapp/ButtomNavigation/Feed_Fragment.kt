 package com.sudeep.blogapp.ButtomNavigation

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.sudeep.blogapp.FeedPostAdapter
import com.sudeep.blogapp.Model.Post
import com.sudeep.blogapp.PostCreate.CreatePostActivity
import com.sudeep.blogapp.R
import kotlinx.android.synthetic.main.activity_create_post.*
import kotlinx.android.synthetic.main.fragment_feed_.*

class Feed_Fragment : Fragment() {
    lateinit var adapter: FeedPostAdapter
    lateinit var recyclerView: RecyclerView
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
        recyclerView = view.findViewById(R.id.recyclerView)
        setUpReclerView();

        }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun setUpReclerView() {
        val firestore = FirebaseFirestore.getInstance()
        val query = firestore.collection("Posts").orderBy("time",Query.Direction.DESCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post::class.java).build()

         adapter = FeedPostAdapter(
            recyclerViewOptions,context!!
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


}
