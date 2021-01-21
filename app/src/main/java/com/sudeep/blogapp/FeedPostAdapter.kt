package com.sudeep.blogapp

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sudeep.blogapp.Model.Post
import com.sudeep.blogapp.R.drawable

class FeedPostAdapter(options:FirestoreRecyclerOptions<Post>,val context: Context):
    FirestoreRecyclerAdapter<Post, FeedViewHolder>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item,parent,false)
        return FeedViewHolder(view)

    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int, model: Post) {
        holder.postText.text=model.text
        holder.postAuthor.text= model.author.name
        holder.postTime.text=Utils.getTimeAgo(model.time)
        holder.likeCount.text=model.likesList.size.toString()

       Glide.with(context)
           .load(model.imageUrl).centerCrop().into(holder.postImage)

        val fireStore=FirebaseFirestore.getInstance()
        val userID=FirebaseAuth.getInstance().currentUser?.uid

        val postDoc=fireStore.collection("Posts").document(snapshots.getSnapshot(holder.adapterPosition).id)
        postDoc.get().addOnCompleteListener {
            val post = it.result?.toObject(Post::class.java)

            if (post?.likesList?.contains(userID)!!) {
                holder.likeIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.like_w
                    )
                )
            } else {
                holder.likeIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.like
                    )
                )
            }

            holder.likeIcon.setOnClickListener {
                if (post.likesList.contains(userID)) {
                    post.likesList.remove(userID)
                    holder.likeIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.like_w
                        )
                    )
                } else {
                    post.likesList.add(userID!!)
                    holder.likeIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.like
                        )
                    )
                }

                postDoc.set(post)
            }
        }

    }
}
class  FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    var postImage: ImageView =itemView.findViewById(R.id.postImage)
    var postText:TextView=itemView.findViewById(R.id.postText)
    var postAuthor:TextView=itemView.findViewById(R.id.postAuthor)
    var postTime:TextView=itemView.findViewById(R.id.postTime)
    var likeIcon:ImageView=itemView.findViewById(R.id.likeIcon)
    var likeCount:TextView=itemView.findViewById(R.id.likeCount)



}