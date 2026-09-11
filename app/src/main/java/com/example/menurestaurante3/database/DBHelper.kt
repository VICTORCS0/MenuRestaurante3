package com.example.menurestaurante3.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "MenuRestaurante.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        // TABLA PRODUCTOS
        db.execSQL("""
            CREATE TABLE productos(
                id_producto INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                descripcion TEXT,
                precio REAL NOT NULL,
                categoria TEXT NOT NULL,
                disponible INTEGER DEFAULT 1
            )
        """.trimIndent())

        // TABLA MESAS
        db.execSQL("""
            CREATE TABLE mesas(
                id_mesa INTEGER PRIMARY KEY,
                estado TEXT DEFAULT 'Disponible'
            )
        """.trimIndent())

        // TABLA PEDIDOS
        db.execSQL("""
            CREATE TABLE pedidos(
                id_pedido INTEGER PRIMARY KEY AUTOINCREMENT,
                id_mesa INTEGER NOT NULL,
                fecha TEXT NOT NULL,
                estado TEXT DEFAULT 'Pendiente',
                total REAL NOT NULL,
                FOREIGN KEY(id_mesa) REFERENCES mesas(id_mesa)
            )
        """.trimIndent())

        // TABLA PRODUCTO_PEDIDO
        db.execSQL("""
            CREATE TABLE producto_pedido(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                id_pedido INTEGER NOT NULL,
                id_producto INTEGER NOT NULL,
                cantidad INTEGER NOT NULL,
                subtotal REAL NOT NULL,
                FOREIGN KEY(id_pedido) REFERENCES pedidos(id_pedido),
                FOREIGN KEY(id_producto) REFERENCES productos(id_producto)
            )
        """.trimIndent())

        // INSERTAR 10 MESAS
        for (i in 1..10) {
            db.execSQL(
                "INSERT INTO mesas(id_mesa, estado) VALUES($i,'Disponible')"
            )
        }

        // INSERTAR PRODUCTOS
        db.execSQL("""
            INSERT INTO productos(nombre,descripcion,precio,categoria,disponible) VALUES

            ('Hot Cakes','Con miel y mantequilla',95,'Desayuno',1),
            ('Omelette','Jamón y queso',90,'Desayuno',1),
            ('Chilaquiles Verdes','Totopos con salsa verde y pollo',110,'Desayuno',1),
            ('Molletes','Pan con frijoles y queso gratinado',85,'Desayuno',1),

            ('Enchiladas Suizas','En salsa verde con queso',180,'Comida',1),
            ('Carne Asada','Acompañada de guarnición',220,'Comida',1),
            ('Mole con Pollo','Pollo con mole poblano',210,'Comida',1),
            ('Pechuga Asada','Con verduras al vapor',195,'Comida',1),

            ('Filete Mignon','Con puré de papa',420,'Cena',1),
            ('Salmón al Limón','Con vegetales',390,'Cena',1),
            ('Pasta Alfredo','Con camarones',360,'Cena',1),
            ('Hamburguesa Gourmet','Carne premium y papas',250,'Cena',1),

            ('Agua Natural','Botella de agua',30,'Bebida',1),
            ('Refresco','Refresco de 600 ml',40,'Bebida',1),
            ('Café Americano','Café recién preparado',45,'Bebida',1),
            ('Té','Té caliente',40,'Bebida',1),
            ('Jugo de Naranja','Natural',50,'Bebida',1),
            ('Limonada','Limonada natural',45,'Bebida',1)

        """.trimIndent())

    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

        db.execSQL("DROP TABLE IF EXISTS producto_pedido")
        db.execSQL("DROP TABLE IF EXISTS pedidos")
        db.execSQL("DROP TABLE IF EXISTS productos")
        db.execSQL("DROP TABLE IF EXISTS mesas")

        onCreate(db)

    }

}