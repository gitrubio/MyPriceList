package com.example.mypricelist

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypricelist.databinding.FragmentHomeBinding

class AdaptadorProductList : RecyclerView.Adapter<AdaptadorProductList.MyViewHolder>() {
    private val listProductList: ArrayList<ProductList> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val cantProduct: TextView = itemView.findViewById(R.id.txtCantProducts)
    }
}

