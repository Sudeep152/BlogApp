package com.sudeep.blogapp.Model

data class Post(val text: String = "",
                val imageUrl: String? = null,
                val author: Users = Users(),
                val time: Long = 0L,
                val likesList: MutableList<String> = mutableListOf())
