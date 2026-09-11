package com.example.menurestaurante3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.menurestaurante3.R
import com.example.menurestaurante3.model.MenuItem

class MenuAdapter(
    private val lista: List<MenuItem>,
    private val alAgregar: (com.example.menurestaurante3.model.Producto) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TIPO_TITULO = 0
        const val TIPO_PRODUCTO = 1
    }

    class TituloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTituloCategoria)
    }

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtDescripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val txtPrecio: TextView = itemView.findViewById(R.id.txtPrecio)
        val btnAgregar: ImageButton = itemView.findViewById(R.id.btnAgregar)
    }

    override fun getItemViewType(position: Int): Int {
        return lista[position].tipo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == TIPO_TITULO) {

            val vista = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_titulo, parent, false)

            TituloViewHolder(vista)

        } else {

            val vista = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_producto, parent, false)

            ProductoViewHolder(vista)

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = lista[position]

        if (holder is TituloViewHolder) {

            holder.txtTitulo.text = item.titulo

        } else if (holder is ProductoViewHolder) {

            val producto = item.producto!!

            holder.txtNombre.text = producto.nombre
            holder.txtDescripcion.text = producto.descripcion
            holder.txtPrecio.text = "$${producto.precio}"
            holder.btnAgregar.setOnClickListener {

                alAgregar(producto)

            }

        }

    }

    override fun getItemCount(): Int {
        return lista.size
    }

}