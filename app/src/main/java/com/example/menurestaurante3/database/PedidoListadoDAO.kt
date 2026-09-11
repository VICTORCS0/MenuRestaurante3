package com.example.menurestaurante3.database

import android.content.ContentValues
import android.content.Context
import com.example.menurestaurante3.model.Pedido
import com.example.menurestaurante3.dao.FirestoreDAO

class PedidoListadoDAO(context: Context) {

    private val dbHelper = DBHelper(context)

    private val firestoreDAO = FirestoreDAO()

    fun obtenerPedidosPendientes(): ArrayList<Pedido> {

        val lista = ArrayList<Pedido>()

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(

            """
            SELECT
                id_pedido,
                id_mesa,
                fecha,
                estado,
                total
            FROM pedidos
            WHERE estado='Pendiente'
            ORDER BY id_pedido DESC
            """.trimIndent(),

            null

        )

        if (cursor.moveToFirst()) {

            do {

                lista.add(

                    Pedido(

                        cursor.getInt(0).toString(),

                        cursor.getInt(1),

                        cursor.getString(2),

                        cursor.getString(3),

                        cursor.getDouble(4)

                    )

                )

            } while (cursor.moveToNext())

        }

        cursor.close()

        db.close()

        return lista

    }

    fun cobrarPedido(idPedido: Int, mesa: Int): Boolean {

        val db = dbHelper.writableDatabase

        val valores = ContentValues()

        valores.put("estado", "Pagado")

        val filas = db.update(

            "pedidos",

            valores,

            "id_pedido=?",

            arrayOf(idPedido.toString())

        )

        val valoresMesa = ContentValues()

        valoresMesa.put("estado", "Disponible")

        db.update(

            "mesas",

            valoresMesa,

            "id_mesa=?",

            arrayOf(mesa.toString())

        )

        db.close()

        if (filas > 0) {

            firestoreDAO.actualizarEstado(
                idPedido.toString()
            )

        }

        return filas > 0

    }

    fun eliminarPedido(idPedido: Int): Boolean {

        val db = dbHelper.writableDatabase

        val filas = db.delete(

            "pedidos",

            "id_pedido=?",

            arrayOf(idPedido.toString())

        )

        db.close()

        if (filas > 0) {

            firestoreDAO.eliminarPedido(
                idPedido.toString()
            )

        }

        return filas > 0

    }

}