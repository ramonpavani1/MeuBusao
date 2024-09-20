package com.example.meubusao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.meubusao.api.AuthResponse
import com.example.meubusao.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Base64



class MainActivity : AppCompatActivity() {

    private var authToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        fetchAuthToken()
        val btnProxima: Button = findViewById(R.id.btn_proxima)
        btnProxima.setOnClickListener {
            val userName = findViewById<EditText>(R.id.et_user_name).text.toString()
            if (userName.isNotEmpty()) {
                Toast.makeText(this, "Bem-vindo, $userName!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, BusActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, insira seu nome", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun fetchAuthToken() {
        val authHeader = "Basic ${
            Base64.encodeToString(
                "YOUR_CLIENT_ID:YOUR_CLIENT_SECRET".toByteArray(),
                Base64.NO_WRAP
            )
        }"

        RetrofitClient.authService.getAuthToken(authHeader)
            .enqueue(object : Callback<AuthResponse> {
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.isSuccessful) {
                        authToken = response.body()?.access_token
                        Toast.makeText(this@MainActivity, "Token Obtido", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Erro: ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Falha: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }

}
