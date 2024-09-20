package com.example.meubusao.api

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("oauth/token")
    fun getAuthToken(
        @Header("Authorization") authHeader: String,
        @Query("grant_type") grantType: String = "client_credentials"
    ): Call<AuthResponse>
}
