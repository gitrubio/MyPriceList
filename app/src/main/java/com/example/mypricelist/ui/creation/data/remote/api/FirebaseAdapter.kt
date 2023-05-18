package com.example.mypricelist.ui.creation.data.remote.api
import com.example.mypricelist.AdaptadorProductList
import com.example.mypricelist.Adapters.ProductAdapter
import com.example.mypricelist.Product
import com.example.mypricelist.ProductList
import com.example.mypricelist.models.ProductModel
import com.google.firebase.database.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseAdapter {

    private val database = FirebaseDatabase.getInstance()
    val db = FirebaseFirestore.getInstance()
    val coleccion: CollectionReference = db.collection("ListMain")

    fun getLists(listadoList: ArrayList<ProductList>, adapter: AdaptadorProductList?) {
        coleccion.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val nuevaList: ProductList = ProductList("" + document.getString("id"),""+document.getString("name"),"10")
                        listadoList.add(nuevaList)
                    }
                    adapter?.notifyDataSetChanged()
                }
            }
    }

    fun listeningList(listadoList: ArrayList<ProductList>, adapter: AdaptadorProductList?) {
    val colecctionRef = db.collection("ListMain")
    colecctionRef.addSnapshotListener { snapshot, e ->
        if (e != null) {
            return@addSnapshotListener
        }
        if (snapshot != null && !snapshot.isEmpty()) {
            listadoList.clear()
            println("+++++++++++++++++++++++++++++")
            println("Current data: ${snapshot.documents}")
            for (document in snapshot.documents) {
                println("document " + document.get("productos"))
                val productos : ArrayList<ProductModel> = document.get("productos") as ArrayList<ProductModel>
                val nuevaList: ProductList = ProductList("" + document.getString("id"),""+document.getString("name"),productos.size.toString())
                listadoList.add(nuevaList)
            }
            adapter?.notifyDataSetChanged()
        } else {
            listadoList.clear()
        }
    }
    }

    fun listeningProducts(products: ArrayList<ProductModel>, adapter: ProductAdapter?) {
        val colecctionRef = db.collection("productos")
        colecctionRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null && !snapshot.isEmpty()) {
                products.clear()
                println("+++++++++++++++++++++++++++++")
                println("Current data: ${snapshot.documents}")
                for (document in snapshot.documents) {
                    val newProduct: ProductModel = ProductModel(""+document.getString("nombre"),""+document.getString("unidad"),1, "" + document.getString("tipo"), 2131230851, "" + document.getString("id"))
                    products.add(newProduct)
                }
                adapter?.notifyDataSetChanged()
            } else {
                products.clear()
            }
        }
    }


}