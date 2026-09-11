package com.example.menurestaurante3.model

data class Producto(
    val idProducto: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val categoria: String,
    val disponible: Int
)