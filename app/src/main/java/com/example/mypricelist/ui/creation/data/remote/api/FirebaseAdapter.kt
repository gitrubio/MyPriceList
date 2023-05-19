package com.example.mypricelist.ui.creation.data.remote.api
import com.example.mypricelist.AdaptadorProductList
import com.example.mypricelist.Adapters.ProductAdapter
import com.example.mypricelist.Product
import com.example.mypricelist.ProductList
import com.example.mypricelist.models.ProductModel
import com.google.firebase.database.*
import com.google.firebase.firestore.CollectionReference
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
                val nuevaList: ProductList = ProductList("" + document.id,""+document.getString("name"),cantidadProductos.toString(), productos)
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
                adapter?.notifyDataSetChanged()
            } else {
                products.clear()
            }
        }
    }


}