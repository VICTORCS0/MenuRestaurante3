package com.example.menurestaurante3.model

data class Pedido(

    val idPedido: String,

    val mesa: Int,

    val fecha: String,

    val estado: String,

    val total: Double

)