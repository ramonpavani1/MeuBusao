package com.example.meubusao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomeActivityActivity : AppCompatActivity() {

    private var authToken: String? = null
    private val apiKey = "6391a69256c4bd04fe3dc864c5eea4f66fffdebaace2fb39ec40abcc26b864af" // Substitua com a sua chave da SPTrans

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Autenticar o token da SPTrans
        authenticateToken(apiKey)

        // Referências para os elementos da interface
        val btnProxima: Button = findViewById(R.id.btn_proxima)
        val btnConfig: Button = findViewById(R.id.btn_config)
        val userNameEditText: EditText = findViewById(R.id.et_user_name)

        // Ação para o botão "Próxima Tela"
        btnProxima.setOnClickListener {
            val userName = userNameEditText.text.toString()
            if (userName.isNotEmpty()) {
                Toast.makeText(this, "Bem-vindo, $userName!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, BusActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, insira seu nome", Toast.LENGTH_SHORT).show()
            }
        }

        // Ação para o botão "Configurações"
        btnConfig.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    // Função para autenticar o token da SPTrans
    private fun authenticateToken(apiKey: String) {
        OlhoVivoClient.olhoVivoApi.authenticate(apiKey).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful && response.body() == true) {
                    Toast.makeText(this@WelcomeActivityActivity, "Autenticação bem-sucedida!", Toast.LENGTH_SHORT).show()
                    authToken = apiKey // Armazena o token para uso futuro
                } else {
                    Toast.makeText(this@WelcomeActivityActivity, "Falha na autenticação", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(this@WelcomeActivityActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
