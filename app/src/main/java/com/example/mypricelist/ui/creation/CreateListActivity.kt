package com.example.mypricelist.ui.creation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.example.mypricelist.R
import com.example.mypricelist.models.ListModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypricelist.AdaptadorProductList
import com.example.mypricelist.Adapters.ProductAdapter
import com.example.mypricelist.models.ProductModel
import com.example.mypricelist.ui.creation.data.remote.api.FirebaseAdapter
import com.example.mypricelist.utils.SinEspaciadoItemDecoration

class CreateListActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val coleccion: CollectionReference = db.collection("ListMain")
    private var dataProducts = ArrayList<ProductModel>()
    private val firebaseAdapter = FirebaseAdapter()
    private val listProducts = (mutableListOf<ProductModel>())
    private var ListAdapter: ProductAdapter?=null
    override fun onStart() {
        super.onStart()
        leerListadoProductos()
    }
    fun leerListadoProductos(){

    }
    fun setDate(data : ArrayList<ProductModel>){
        dataProducts = data
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_list)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        // botón de retroceso en el Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // evento para el botón de retroceso
        toolbar.setNavigationOnClickListener { onBackPressed() }
        val spinner = findViewById<Spinner>(R.id.spiProducts)
        dataProducts = firebaseAdapter.getProducts(spinner,this,::setDate)


        val recyclerView: RecyclerView = findViewById(R.id.ReView)
        // instancia del adaptador
        ListAdapter = ProductAdapter(listProducts)
        recyclerView.adapter = ListAdapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager



    }

    override fun onDestroy() {
        super.onDestroy()
        overridePendingTransition(0, 0)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.toolbar_nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finishAfterTransition()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun addProductList(view: View){
        println(dataProducts)
        println(dataProducts.size === 0)
        if(dataProducts.size === 0){
            Toast.makeText(this, "No hay productos seleccionados", Toast.LENGTH_SHORT).show()
        }else{
            val controlSelectProduct = findViewById<Spinner>(R.id.spiProducts)
            val product = dataProducts[controlSelectProduct.selectedItemPosition]
            if (!listProducts.any { it.nombre == product.nombre }) {
                listProducts.add(product)
            }else{
                for (item in listProducts) {
                    if (item.nombre == product.nombre ) {
                        item.cantidad += 1
                        break
                    }
                }
            }
            ListAdapter?.notifyDataSetChanged()
        }

    }
    fun saveList(view : View){
        var controListName = findViewById<EditText>(R.id.edtListName)

        var correctly = controlErrors(controListName)
        if(correctly){
            val producto = ListModel(controListName.text.toString(),120.0, listProducts)
            coleccion.add(producto)
            finishAfterTransition()
        }
    }

    private fun controlErrors(listName : EditText, ): Boolean {
        var correctlyData = true
        var nameList = listName.text.toString()
        if(nameList.isEmpty()){
            listName.error = "Por favor Ingrese un nombre para la lista"
            correctlyData = false
        }
        if(listProducts.isEmpty()){
            Toast.makeText(this, "Debe tener minimo un producto en la lista", Toast.LENGTH_SHORT).show()
            correctlyData = false
        }
        return correctlyData
    }
}