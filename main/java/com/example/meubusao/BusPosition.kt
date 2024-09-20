package com.example.meubusao.api

data class BusPositionResponse(
    val hr: String,
    val l: List<BusLine>
)

data class BusLine(
    val c: String,  // Código da linha
    val cl: Int,    // Identificador da linha
    val sl: Int,    // Sentido da linha
    val lt0: String, // Terminal 1
    val lt1: String, // Terminal 2
    val qv: Int,    // Quantidade de veículos
    val vs: List<BusVehicle>
)

data class BusVehicle(
    val p: String,   // Placa do veículo
    val a: Boolean,  // Acessibilidade
    val ta: String,  // Horário da última atualização
    val py: Double,  // Latitude
    val px: Double   // Longitude
)