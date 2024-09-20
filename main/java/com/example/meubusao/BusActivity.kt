package com.example.meubusao

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meubusao.adapter.BusAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var busAdapter: BusAdapter
    private var authToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus)

        // Configura o RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Carregar linhas de ônibus se o token estiver autenticado
        authToken?.let {
            fetchLinhas(it)
        } ?: run {
            Toast.makeText(this, "Token não autenticado. Faça login novamente.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchLinhas(token: String) {
        val authHeader = "Bearer $token"
        OlhoVivoClient.olhoVivoApi.getLinhas(authHeader, "termo").enqueue(object : Callback<LinhasResponse> {
            override fun onResponse(call: Call<LinhasResponse>, response: Response<LinhasResponse>) {
                if (response.isSuccessful) {
                    val linha = response.body()?.linhas ?: emptyList()
                    busAdapter = BusAdapter(linha)
                    recyclerView.adapter = busAdapter
                } else {
                    Toast.makeText(this@BusActivity, "Erro: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LinhasResponse>, t: Throwable) {
                Toast.makeText(this@BusActivity, "Falha: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
