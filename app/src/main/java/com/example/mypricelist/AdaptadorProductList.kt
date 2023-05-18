package com.example.mypricelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
        val productList: ProductList = listProductList[position]
        holder.bind(productList)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val titulo: TextView = itemView.findViewById(R.id.txtTitulo)
        private val cantProduct: TextView = itemView.findViewById(R.id.txtCantProducts)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(productList: ProductList) {
            titulo.text = productList.tituloNota
            cantProduct.text = productList.cantNota
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val productList = listProductList[position]
                val toastMessage = "Título: ${productList.id}, Cantidad: ${productList.cantNota}"
                Toast.makeText(view.context, toastMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}