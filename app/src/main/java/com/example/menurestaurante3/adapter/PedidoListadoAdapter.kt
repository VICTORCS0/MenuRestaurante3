package com.example.menurestaurante3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.menurestaurante3.R
import com.example.menurestaurante3.model.Pedido

class PedidoListadoAdapter(

    private val lista: List<Pedido>,

    private val alCobrar:(Pedido)->Unit

): RecyclerView.Adapter<PedidoListadoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val txtMesa:TextView=itemView.findViewById(R.id.txtMesa)

        val txtPedido:TextView=itemView.findViewById(R.id.txtPedido)

        val txtFecha:TextView=itemView.findViewById(R.id.txtFecha)

        val txtEstado:TextView=itemView.findViewById(R.id.txtEstado)

        val txtTotal:TextView=itemView.findViewById(R.id.txtTotalPedido)

        val btnCobrar:Button=itemView.findViewById(R.id.btnCobrar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val vista=LayoutInflater.from(parent.context)

            .inflate(R.layout.item_pedido_guardado,parent,false)

        return ViewHolder(vista)

    }

    override fun getItemCount()=lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pedido=lista[position]

        holder.txtMesa.text="Mesa ${pedido.mesa}"

        holder.txtPedido.text="Pedido #${pedido.idPedido}"

        holder.txtFecha.text=pedido.fecha

        holder.txtEstado.text=pedido.estado

        holder.txtTotal.text="Total: $${pedido.total}"

        holder.btnCobrar.setOnClickListener{

            alCobrar(pedido)

        }

    }

}