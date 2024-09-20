package com.example.meubusao.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface OlhoVivoApi {

    @GET("api/v1/linhas")
    fun getLinhas(
        @Header("Authorization") authHeader: String,
        @Query("parametro") parametro: String
    ): Call<LinhasResponse>
}
