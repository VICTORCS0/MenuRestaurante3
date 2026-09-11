package com.example.menurestaurante3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menurestaurante3.adapter.PedidoListadoAdapter
import com.example.menurestaurante3.database.PedidoListadoDAO

class PedidosActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView

    private lateinit var dao: PedidoListadoDAO

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pedidos)

        recycler = findViewById(R.id.rvPedidos)

        dao = PedidoListadoDAO(this)

        cargarPedidos()

    }

    private fun cargarPedidos() {

        val lista = dao.obtenerPedidosPendientes()

        recycler.layoutManager = LinearLayoutManager(this)

        recycler.adapter = PedidoListadoAdapter(lista) { pedido ->

            AlertDialog.Builder(this)

                .setTitle("Pedido")

                .setMessage("¿Qué deseas hacer con el pedido de la Mesa ${pedido.mesa}?")

                .setPositiveButton("Cobrar") { _, _ ->

                    if (dao.cobrarPedido(pedido.idPedido.toInt(), pedido.mesa)) {

                        Toast.makeText(

                            this,

                            "Pedido cobrado",

                            Toast.LENGTH_SHORT

                        ).show()

                        cargarPedidos()

                    }

                }

                .setNeutralButton("Eliminar") { _, _ ->

                    if (dao.eliminarPedido(pedido.idPedido.toInt())) {

                        Toast.makeText(

                            this,

                            "Pedido eliminado",

                            Toast.LENGTH_SHORT

                        ).show()

                        cargarPedidos()

                    }

                }

                .setNegativeButton("Cancelar", null)

                .show()

        }

    }

}