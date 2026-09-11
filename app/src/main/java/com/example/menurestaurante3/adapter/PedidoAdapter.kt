package com.example.menurestaurante3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.menurestaurante3.R
import com.example.menurestaurante3.model.PedidoItem

class PedidoAdapter(

    private val lista: ArrayList<PedidoItem>,
    private val onMasClick: (PedidoItem) -> Unit,
    private val onMenosClick: (PedidoItem) -> Unit

) : RecyclerView.Adapter<PedidoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtNombre: TextView =
            itemView.findViewById(R.id.txtNombrePedido)

        val txtCantidad: TextView =
            itemView.findViewById(R.id.txtCantidad)

        val btnMenos: ImageButton = itemView.findViewById(R.id.btnMenos)

        val btnMas: ImageButton = itemView.findViewById(R.id.btnMas)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pedido, parent, false)

        return ViewHolder(vista)

    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val item = lista[position]

        holder.txtNombre.text = item.producto.nombre

        holder.txtCantidad.text = item.cantidad.toString()

        holder.btnMas.setOnClickListener {

            onMasClick(item)

        }

        holder.btnMenos.setOnClickListener {

            onMenosClick(item)

        }

    }

    override fun getItemCount(): Int {

        return lista.size

    }

}