package com.example.mypricelist.data.remote.api
import com.example.mypricelist.AdaptadorProductList
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


}