package com.example.meubusao.api

data class LinhasResponse(
    val linhas: List<Linha>
)

data class Linha(
    val id: String,
    val nome: String
)
