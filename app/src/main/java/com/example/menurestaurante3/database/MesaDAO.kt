package com.example.menurestaurante3.database

import android.content.Context

class MesaDAO(context: Context) {

    private val dbHelper = DBHelper(context)

    fun obtenerMesas(): ArrayList<String> {

        val lista = ArrayList<String>()

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT id_mesa, estado FROM mesas ORDER BY id_mesa",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                val idMesa = cursor.getInt(0)
                val estado = cursor.getString(1)

                if (estado == "Ocupada") {

                    lista.add("Mesa $idMesa (Ocupada)")

                } else {

                    lista.add("Mesa $idMesa")

                }

            } while (cursor.moveToNext())

        }

        cursor.close()
        db.close()

        return lista

    }

    fun mesaOcupada(idMesa: Int): Boolean {

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT estado FROM mesas WHERE id_mesa=?",
            arrayOf(idMesa.toString())
        )

        var ocupada = false

        if (cursor.moveToFirst()) {

            ocupada = cursor.getString(0) == "Ocupada"

        }

        cursor.close()
        db.close()

        return ocupada

    }

    fun liberarTodasLasMesas() {

        val db = dbHelper.writableDatabase

        db.execSQL(

            "UPDATE mesas SET estado='Disponible'"

        )

        db.close()

    }

}