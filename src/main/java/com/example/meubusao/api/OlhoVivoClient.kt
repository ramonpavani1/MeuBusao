package com.example.meubusao

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

// Interface para interagir com a API da SPTrans
interface OlhoVivoApi {

    @POST("Login/Autenticar")
    fun authenticate(@Query("token") token: String): Call<Boolean>

    // Exemplo de endpoint para buscar as linhas de ônibus (você pode adicionar mais)
    @GET("Linha/Buscar")
    fun getLinhas(@Header("Authorization") authHeader: String, @Query("termo") termo: String): Call<LinhasResponse>
}

// Cliente Retrofit para acessar a API da SPTrans
object OlhoVivoClient {

    private const val BASE_URL = "http://api.olhovivo.sptrans.com.br/v2.1/"

    val olhoVivoApi: OlhoVivoApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OlhoVivoApi::class.java)
    }
}

// Modelo de resposta para as linhas de ônibus (exemplo básico)
data class LinhasResponse(
    val linhas: List<Linha>
)

data class Linha(
    val codigo: Int,
    val nome: String,
    val letreiro: String
)
