package com.sudeep.blogapp.UserAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.auth.User
import com.sudeep.blogapp.Model.Post
import com.sudeep.blogapp.Model.Users
import com.sudeep.blogapp.R

class userAdapter(options: FirestoreRecyclerOptions<Users>,val context: Context):FirestoreRecyclerAdapter<Users,UserViewHolder>(options){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: Users) {

        holder.usreName.text=model.name


    }


}
class  UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    var profileImg= itemView.findViewById<ImageView>(R.id.profileImage)
    var usreName: TextView = itemView.findViewById(R.id.userName)
    var msgButton:Button=itemView.findViewById(R.id.msgButton)

}