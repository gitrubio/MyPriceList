package com.example.mypricelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypricelist.databinding.FragmentHomeBinding

class AdaptadorProductList(private val listProductList: ArrayList<ProductList>) : RecyclerView.Adapter<AdaptadorProductList.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listProductList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val producListActual: ProductList = listProductList.get(position)
        holder.titulo.setText(producListActual.tituloNota)
        holder.cantProduct.setText(producListActual.cantNota)
        holder.total.setText(producListActual.totalNota)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val cantProduct: TextView = itemView.findViewById(R.id.txtCantProducts)
        val total : TextView = itemView.findViewById(R.id.txtTotal)
    }
}

