package com.example.menurestaurante3.model

data class MenuItem(

    val tipo: Int,

    val titulo: String = "",

    val producto: Producto? = null

)