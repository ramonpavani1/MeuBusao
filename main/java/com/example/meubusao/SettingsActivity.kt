package com.example.meubusao

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var notificationSwitch: Switch
    private lateinit var themeTextView: TextView
    private lateinit var dataUsageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Referências para os elementos da interface
        notificationSwitch = findViewById(R.id.switch_notifications)
        themeTextView = findViewById(R.id.text_theme)
        dataUsageTextView = findViewById(R.id.text_data_usage)

        // Recuperar preferências armazenadas
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val notificationsEnabled = sharedPreferences.getBoolean("notifications_enabled", true)
        val darkModeEnabled = sharedPreferences.getBoolean("dark_mode", false)

        // Configurar o estado inicial dos controles
        notificationSwitch.isChecked = notificationsEnabled
        themeTextView.text = if (darkModeEnabled) "Modo Escuro" else "Modo Claro"

        // Configurar o listener para o switch de notificações
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("notifications_enabled", isChecked).apply()
        }

        // Configurar o listener para a mudança de tema
        themeTextView.setOnClickListener {
            val newDarkMode = !darkModeEnabled
            sharedPreferences.edit().putBoolean("dark_mode", newDarkMode).apply()
            themeTextView.text = if (newDarkMode) "Modo Escuro" else "Modo Claro"
        }

        // Configurar o listener para o uso de dados
        dataUsageTextView.setOnClickListener {
            // Exemplo de ação para ajuste de preferências de dados
            // Pode abrir um diálogo ou uma nova atividade para configuração detalhada
            showDataUsageDialog()
        }
    }

    private fun showDataUsageDialog() {
        // Implementar a lógica para mostrar um diálogo ou nova tela para configuração de uso de dados
    }
}
