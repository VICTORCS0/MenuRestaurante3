package com.example.menurestaurante3.controller

import com.example.menurestaurante3.model.PedidoItem
import com.example.menurestaurante3.model.Producto

class PedidoController {

    private val pedido = ArrayList<PedidoItem>()

    fun agregarProducto(producto: Producto) {

        val existente = pedido.find {

            it.producto.idProducto == producto.idProducto

        }

        if (existente != null) {

            existente.cantidad++

        } else {

            pedido.add(

                PedidoItem(producto, 1)

            )

        }

    }

    fun obtenerPedido(): ArrayList<PedidoItem> {

        return pedido

    }

    fun limpiarPedido() {

        pedido.clear()

    }

    fun quitarProducto(producto: Producto) {

        val existente = pedido.find {

            it.producto.idProducto == producto.idProducto

        }

        if (existente != null) {

            existente.cantidad--

            if (existente.cantidad <= 0) {

                pedido.remove(existente)

            }

        }

    }

    fun calcularTotal(): Double {

        var total = 0.0

        for (item in pedido) {

            total += item.subtotal()

        }

        return total

    }


}