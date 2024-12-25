package com.example.mvcpattern

data class Response(
    val success: Boolean,
    val message: String,
    val data: Any? = null
)
