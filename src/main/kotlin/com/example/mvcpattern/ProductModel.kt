package com.example.mvcpattern

import jakarta.persistence.*

@Entity
data class ProductModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String = "",

    val price: Double = 0.0,

    @Version
    val version: Int = 0
)
