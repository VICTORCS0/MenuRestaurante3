package com.example.menurestaurante3.database

import android.content.ContentValues
import android.content.Context
import com.example.menurestaurante3.model.PedidoItem
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PedidoDAO(context: Context) {

    private val dbHelper = DBHelper(context)

    private val firestore = FirebaseFirestore.getInstance()

    fun guardarPedido(
        idMesa: Int,
        lista: ArrayList<PedidoItem>,
        total: Double
    ): Boolean {

        val db = dbHelper.writableDatabase

        return try {

            // Pedido
            val pedido = ContentValues()

            pedido.put("id_mesa", idMesa)
            pedido.put("fecha", System.currentTimeMillis().toString())
            pedido.put("estado", "Pendiente")
            pedido.put("total", total)

            val idPedido = db.insert(
                "pedidos",
                null,
                pedido
            )

            val productosFirebase = ArrayList<HashMap<String, Any>>()

            for (item in lista) {

                val producto = hashMapOf<String, Any>()

                producto["nombre"] = item.producto.nombre
                producto["cantidad"] = item.cantidad
                producto["precio"] = item.producto.precio

                productosFirebase.add(producto)

            }

            // Productos del pedido
            for (item in lista) {

                val detalle = ContentValues()

                detalle.put("id_pedido", idPedido)

                detalle.put(
                    "id_producto",
                    item.producto.idProducto
                )

                detalle.put(
                    "cantidad",
                    item.cantidad
                )

                detalle.put(
                    "subtotal",
                    item.cantidad * item.producto.precio
                )

                db.insert(
                    "producto_pedido",
                    null,
                    detalle
                )

            }

            // Mesa ocupada
            val mesa = ContentValues()

            mesa.put(
                "estado",
                "Ocupada"
            )

            db.update(
                "mesas",
                mesa,
                "id_mesa=?",
                arrayOf(idMesa.toString())
            )

            val pedidoFirestore = hashMapOf(

                "idPedido" to idPedido.toString(),

                "mesa" to idMesa,

                "fecha" to SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()
                ).format(Date()),

                "estado" to "Pendiente",

                "total" to total,

                "productos" to productosFirebase

            )

            firestore.collection("pedidos")
                .document(idPedido.toString())
                .set(pedidoFirestore)

            db.close()

            true

        } catch (e: Exception) {

            db.close()

            false

        }

    }

}