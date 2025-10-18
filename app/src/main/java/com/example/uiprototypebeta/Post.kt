package com.example.uiprototypebeta

import java.io.Serializable

data class Post(
    val id: Int,
    val title: String,
    val snippet: String,
    val body: String,
    val tags: List<String>,
    val date: String,
    val author: String,
    val readTime: String
) : Serializable
