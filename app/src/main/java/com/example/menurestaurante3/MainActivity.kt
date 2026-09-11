package com.example.menurestaurante3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.menurestaurante3.database.ProductoDAO
import com.example.menurestaurante3.model.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menurestaurante3.adapter.MenuAdapter
import com.example.menurestaurante3.controller.PedidoController
import com.example.menurestaurante3.adapter.PedidoAdapter
import android.widget.TextView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Button
import com.example.menurestaurante3.database.PedidoDAO
import com.example.menurestaurante3.database.MesaDAO
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private val pedidoController = PedidoController()

    private lateinit var pedidoAdapter: PedidoAdapter

    private lateinit var txtTotal: TextView

    private lateinit var spMesa: Spinner

    private lateinit var adapterMesa: ArrayAdapter<String>

    private lateinit var mesaDAO: MesaDAO

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.rvMenu)

        val rvPedido = findViewById<RecyclerView>(R.id.rvPedido)

        txtTotal = findViewById(R.id.txtTotal)

        val btnRealizarPedido = findViewById<Button>(R.id.btnRealizarPedido)

        val btnPedidos = findViewById<Button>(R.id.btnPedidos)

        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        spMesa = findViewById(R.id.spMesa)

        val dao = ProductoDAO(this)

        val pedidoDAO = PedidoDAO(this)

        mesaDAO = MesaDAO(this)

        val productos = dao.obtenerProductos()

        val mesas = mesaDAO.obtenerMesas()

        adapterMesa = ArrayAdapter(

            this,

            android.R.layout.simple_spinner_item,

            mesas

        )

        adapterMesa.setDropDownViewResource(

            android.R.layout.simple_spinner_dropdown_item

        )

        spMesa.adapter = adapterMesa

        Toast.makeText(
            this,
            "Productos: ${productos.size}",
            Toast.LENGTH_LONG
        ).show()

        val lista = arrayListOf<MenuItem>()

        var categoriaActual = ""

        for (producto in productos) {

            val categoria = producto.categoria.uppercase()

            if (categoria != categoriaActual) {

                categoriaActual = categoria

                lista.add(
                    MenuItem(
                        tipo = 0,
                        titulo = categoria
                    )
                )
            }

            lista.add(

                MenuItem(
                    tipo = 1,
                    producto = producto
                )

            )

        }



        // Recycler del pedido
        rvPedido.layoutManager = LinearLayoutManager(this)

        pedidoAdapter = PedidoAdapter(

            pedidoController.obtenerPedido(),

            { item ->

                pedidoController.agregarProducto(item.producto)

                pedidoAdapter.notifyDataSetChanged()

                actualizarTotal()

            },

            { item ->

                pedidoController.quitarProducto(item.producto)

                pedidoAdapter.notifyDataSetChanged()

                actualizarTotal()

            }

        )

        rvPedido.adapter = pedidoAdapter

// Recycler del menú
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = MenuAdapter(lista) { producto ->

            pedidoController.agregarProducto(producto)

            pedidoAdapter.notifyDataSetChanged()

            actualizarTotal()

        }

        btnRealizarPedido.setOnClickListener {

            if (pedidoController.obtenerPedido().isEmpty()) {

                Toast.makeText(
                    this,
                    "Agrega al menos un producto.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener

            }

            val numeroMesa = spMesa.selectedItemPosition + 1

            if (mesaDAO.mesaOcupada(numeroMesa)) {

                Toast.makeText(
                    this,
                    "Esta mesa ya tiene un pedido activo.",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener

            }

            var total = 0.0

            for (item in pedidoController.obtenerPedido()) {

                total += item.producto.precio * item.cantidad

            }

            val guardado = pedidoDAO.guardarPedido(

                numeroMesa,

                pedidoController.obtenerPedido(),

                total

            )

            if (guardado) {

                Toast.makeText(

                    this,

                    "Pedido guardado correctamente",

                    Toast.LENGTH_LONG

                ).show()

                pedidoController.limpiarPedido()

                pedidoAdapter.notifyDataSetChanged()

                actualizarTotal()

                actualizarSpinnerMesas()

            } else {

                Toast.makeText(

                    this,

                    "Error al guardar el pedido",

                    Toast.LENGTH_LONG

                ).show()

            }

        }


        btnPedidos.setOnClickListener {

            startActivity(

                Intent(this, PedidosActivity::class.java)

            )

        }

        btnCerrarSesion.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()

        }

    }

    private fun actualizarTotal() {

        var total = 0.0

        for (item in pedidoController.obtenerPedido()) {

            total += item.producto.precio * item.cantidad

        }

        txtTotal.text = "Total: $%.2f".format(total)

    }

    override fun onResume() {

        super.onResume()

        actualizarSpinnerMesas()

    }

    private fun actualizarSpinnerMesas() {

        adapterMesa.clear()

        adapterMesa.addAll(

            mesaDAO.obtenerMesas()

        )

        adapterMesa.notifyDataSetChanged()

    }



}