package com.example.orufyassign.di.model

data class AnimalResponse(
    val id: Int,
    val tags: String,
    val webformatURL: String
)

data class ApiResponse(
    val hits: List<AnimalResponse>
)