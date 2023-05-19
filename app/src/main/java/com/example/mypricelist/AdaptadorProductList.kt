package com.example.mypricelist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypricelist.ui.creation.ShowProductsByList

class AdaptadorProductList(private val listProductList: ArrayList<ProductList>, private val context: Context) : RecyclerView.Adapter<AdaptadorProductList.MyViewHolder>() {

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

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val productList = listProductList[position]

                val intent = Intent(context, ShowProductsByList::class.java)

                intent.putExtra("id", productList.id)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }
}