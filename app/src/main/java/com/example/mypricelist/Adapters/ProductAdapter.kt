package com.example.mypricelist.Adapters


import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypricelist.R
import com.example.mypricelist.models.ProductModel

class ProductAdapter(val listaObjetos: List<ProductModel>) : RecyclerView.Adapter<ProductAdapter.ObjetoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjetoViewHolder {
        // Inflar el diseño de la vista del elemento de lista
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_product_item, parent, false)
        return ObjetoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ObjetoViewHolder, position: Int) {
        val objeto = listaObjetos[position]
        holder.txtNombre.text = objeto.nombre
        //val uri = Uri.parse("res/drawable/french_fries.png")
        //holder.imgProduct.setImageURI(uri)
        holder.txtCantidad.text = objeto.cantidad.toString()
    }

    override fun getItemCount(): Int {
        // Devolver el número de elementos en la lista de objetos
        return listaObjetos.size
    }

    class ObjetoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val imgProduct : ImageView = itemView.findViewById(R.id.imageProduct)
        val txtCantidad: TextView = itemView.findViewById(R.id.txtCantidad)
        //val txtTipo: TextView = itemView.findViewById(R.id.txtTipo)
    }
}