package com.example.menurestaurante3.model

data class PedidoItem(

    val producto: Producto,

    var cantidad: Int = 1

) {

    fun subtotal(): Double {

        return producto.precio * cantidad

    }

}