package com.sudeep.blogapp.PostCreate

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.text.TextUtils
import android.widget.Toast
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.sudeep.blogapp.Model.Post
import com.sudeep.blogapp.Model.Users
import com.sudeep.blogapp.R
import kotlinx.android.synthetic.main.activity_create_post.*
import kotlinx.android.synthetic.main.add_post.*
import kotlinx.android.synthetic.main.fragment_feed_.*
import java.io.File

class CreatePostActivity : AppCompatActivity() {
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)


        postButton.setOnClickListener {
//
//            progressDialog.show()
            val text = postText.text.toString()

            if(TextUtils.isEmpty(text) ) {
                Toast.makeText(this, "Description cannot be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            postUpload(text)
        }

        postImage.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }




    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data
            postImage.setImageURI(fileUri)

            imageUri = fileUri
            //You can get File object from intent
            val file: File = ImagePicker.getFile(data)!!

            //You can also get File Path from intent
            val filePath:String = ImagePicker.getFilePath(data)!!
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    fun postUpload( text :String ){
        val    progressDialog = ProgressDialog(this@CreatePostActivity)


        progressDialog.setMessage("Loading...")

        progressDialog.show()
        val firestore = FirebaseFirestore.getInstance()
         firestore.collection("Users")
             .document(FirebaseAuth.getInstance().currentUser?.uid!!).get()
             .addOnCompleteListener {
                 val user= it.result?.toObject(Users::class.java)

                 val storage=  FirebaseStorage.getInstance().reference.child("Images")
                     .child(FirebaseAuth.getInstance().currentUser?.email!!.toString() + "_" + System.currentTimeMillis() + ".jpg")

                 val uploadTask= imageUri?.let { it1 -> storage.putFile(it1) }
//
                 val urlTask= uploadTask?.continueWithTask{ task->
                     if (!task.isSuccessful){
                         Toast.makeText(this,"UploadTask",Toast.LENGTH_LONG).show()
                     }
                     storage.downloadUrl
                 }?.addOnCompleteListener { urlTaskCompleted ->
                     if(urlTaskCompleted.isSuccessful) {
                         val downloadUri = urlTaskCompleted.result

                         val post = Post(text,downloadUri.toString()!!, user!!,
                             System.currentTimeMillis())

                         firestore.collection("Posts").document().set(post!!)
                             .addOnCompleteListener { posted ->
                                 if(posted.isSuccessful) {
                                     Toast.makeText(this, "Posted", Toast.LENGTH_LONG).show()
                                     progressDialog.dismiss()


                                     finish()
                                 } else {
                                     Toast.makeText(this, "Error occurred. Please try again.", Toast.LENGTH_LONG).show()
                                 }
                             }
                     }
                 }

             }
    }
}