package com.sudeep.blogapp.ButtomNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.sudeep.blogapp.FeedPostAdapter
import com.sudeep.blogapp.Model.Post
import com.sudeep.blogapp.Model.Users
import com.sudeep.blogapp.R
import com.sudeep.blogapp.UserAdapter.userAdapter

class MessageFragment : Fragment() {

     lateinit var userRecyclerView:RecyclerView
     lateinit var adapter:userAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecyclerView=view.findViewById(R.id.userRv)


        setUpRecylerView()
    }

    private fun setUpRecylerView() {
        val firestore = FirebaseFirestore.getInstance()
        val query = firestore.collection("Users")
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Users>().setQuery(query, Users::class.java).build()

        adapter = userAdapter(
            recyclerViewOptions,requireContext()
        )

        userRecyclerView.adapter = adapter
        userRecyclerView.layoutManager = LinearLayoutManager(activity)
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