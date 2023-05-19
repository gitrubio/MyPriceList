package com.example.mypricelist.ui.creation.data.remote.api
import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.mypricelist.AdaptadorProductList
import com.example.mypricelist.Adapters.ProductAdapter
import com.example.mypricelist.ProductList
import com.example.mypricelist.models.ProductModel
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class FirebaseAdapter {

    private val database = FirebaseDatabase.getInstance()
    val db = FirebaseFirestore.getInstance()
//    val coleccion: CollectionReference = db.collection("ListMain")

//    fun getLists(listadoList: ArrayList<ProductList>, adapter: AdaptadorProductList?) {
//        coleccion.get()
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    for (document in task.result) {
//                        val nuevaList: ProductList = ProductList("" + document.getString("id"),""+document.getString("name"),"10")
//                        listadoList.add(nuevaList)
//                    }
//                    adapter?.notifyDataSetChanged()
//                }
//            }
//    }

    fun listeningList(listadoList: ArrayList<ProductList>, adapter: AdaptadorProductList?) {
    val colecctionRef = db.collection("ListMain")
    colecctionRef.addSnapshotListener { snapshot, e ->
        if (e != null) {
            return@addSnapshotListener
        }
        if (snapshot != null && !snapshot.isEmpty()) {
            listadoList.clear()

            for (document in snapshot.documents) {
                val productosDoc = document.get("productos") as? ArrayList<*>
                val productos = transformData(productosDoc)
                val cantidadProductos = productos.sumOf { it.cantidad }
                val nuevaList: ProductList = ProductList("" + document.id,""+document.getString("name"),cantidadProductos.toString(),document.get("total").toString(), productos)
                listadoList.add(nuevaList)
            }
            adapter?.notifyDataSetChanged()
        } else {
            listadoList.clear()
        }
    }
    }
    fun listeningProductsByList(
        products: ArrayList<ProductModel>,
        listId: String,
        adapter: ProductAdapter?
    ): ListenerRegistration? {

        val db = FirebaseFirestore.getInstance()
        val documentRef = db.collection("ListMain").document(listId)

        val listener = documentRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                // Manejar el error
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                products.clear()
                val data = snapshot.data
                if (data != null) {
                    val productosDoc = data["productos"] as? ArrayList<*>
                    val productos = transformData(productosDoc)
                    products.clear()
                    products.addAll(productos)

                    productos.map { ele-> print("----------------------" + ele) }
                }
                adapter?.notifyDataSetChanged()
            } else {
                products.clear()
            }
        }

        return listener
    }



    fun transformData( productos : ArrayList<*>?) : ArrayList<ProductModel>  {
        val productList =  ArrayList<ProductModel>()
        if (productos != null) {
            for (producto in productos) {
                if (producto is HashMap<*, * >) {
                    productList.add(ProductModel(
                        cantidad =  producto["cantidad"].toString().toInt(),
                        nombre = producto["nombre"].toString(),
                        unidad = producto["unidad"].toString(),
                        tipo = producto["tipo"].toString(),
                        imgID = producto["imgID"].toString().toInt()
                    ))
                }
            }
        }
        return productList
    }
    fun listeningProducts(products: ArrayList<ProductModel>, adapter: ProductAdapter?) {
        val colecctionRef = db.collection("productos")
        colecctionRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null && !snapshot.isEmpty()) {
                products.clear()

                for (document in snapshot.documents) {
                    val newProduct: ProductModel = ProductModel(""+document.getString("nombre"),""+document.getString("unidad"),1, "" + document.getString("tipo"), 2131230851, "" + document.getString("id"))
                    products.add(newProduct)
                }
                if( adapter !== null ) adapter?.notifyDataSetChanged()
            } else {
                products.clear()
            }
        }
    }

    fun getProducts( spinner :  Spinner, context : Context, call : (ArrayList<ProductModel>) -> Unit) {
        val productos = ArrayList<ProductModel>()
        val colecctionRef = db.collection("productos")
         colecctionRef.get().addOnSuccessListener { result ->
                println("que es result"+result)
            for (document in result) {
                productos.add(ProductModel(
                    cantidad =  document["cantidad"].toString().toInt(),
                    nombre = document["nombre"].toString(),
                    unidad = document["unidad"].toString(),
                    tipo = document["tipo"].toString(),
                    imgID = document["imgID"].toString().toInt()
                ))
            }
             val data = productos.map { it.nombre }
             val adapter = ArrayAdapter(context, R.layout.simple_spinner_dropdown_item, data)
             spinner.adapter = adapter
        }.addOnFailureListener { exception ->
             // Maneja la falla en caso de error
             // Por ejemplo, muestra un mensaje de error
             println( "Error al obtener documentos: "+ exception)
         }

    }
}