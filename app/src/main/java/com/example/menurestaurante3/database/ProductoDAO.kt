package com.example.menurestaurante3.database

import android.content.ContentValues
import android.content.Context
import com.example.menurestaurante3.model.Producto

class ProductoDAO(context: Context) {

    private val dbHelper = DBHelper(context)

    fun insertarProducto(producto: Producto) {

        val db = dbHelper.writableDatabase

        val valores = ContentValues()

        valores.put("nombre", producto.nombre)
        valores.put("descripcion", producto.descripcion)
        valores.put("precio", producto.precio)
        valores.put("categoria", producto.categoria)
        valores.put("disponible", producto.disponible)

        db.insert("productos", null, valores)

        db.close()
    }

    fun obtenerProductos(): ArrayList<Producto> {

        val lista = ArrayList<Producto>()

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            """
    SELECT * FROM productos
    ORDER BY
    CASE categoria
        WHEN 'Desayuno' THEN 1
        WHEN 'Comida' THEN 2
        WHEN 'Cena' THEN 3
        WHEN 'Bebida' THEN 4
    END,
    nombre
    """.trimIndent(),
            null
        )

        if (cursor.moveToFirst()) {

            do {

                lista.add(

                    Producto(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getString(4),
                        cursor.getInt(5)
                    )

                )

            } while (cursor.moveToNext())

        }

        cursor.close()
        db.close()

        return lista

    }

}