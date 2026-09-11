package com.example.menurestaurante3.dao

import com.google.firebase.firestore.FirebaseFirestore
import com.example.menurestaurante3.model.Pedido
import com.example.menurestaurante3.model.PedidoItem

class FirestoreDAO {

    private val firestore = FirebaseFirestore.getInstance()

    fun guardarPedido(
        pedido: Pedido,
        productos: ArrayList<PedidoItem>
    ) {

        val listaProductos = ArrayList<HashMap<String, Any>>()

        for(item in productos){

            val mapa = hashMapOf<String, Any>()

            mapa["nombre"] = item.producto.nombre
            mapa["cantidad"] = item.cantidad
            mapa["precio"] = item.producto.precio

            listaProductos.add(mapa)

        }

        val datos = hashMapOf(

            "idPedido" to pedido.idPedido,
            "mesa" to pedido.mesa,
            "fecha" to pedido.fecha,
            "estado" to pedido.estado,
            "total" to pedido.total,
            "productos" to listaProductos

        )

        firestore.collection("pedidos")
            .document(pedido.idPedido)
            .set(datos)

    }

    fun actualizarEstado(idPedido: String) {

        firestore.collection("pedidos")
            .document(idPedido)
            .update(
                "estado",
                "Cobrado"
            )

    }

    fun eliminarPedido(idPedido: String) {

        firestore.collection("pedidos")
            .document(idPedido)
            .delete()

    }


    fun actualizarEstado(
        idPedido:String,
        nuevoEstado:String
    ){

        firestore.collection("pedidos")
            .document(idPedido)
            .update("estado",nuevoEstado)

    }


}