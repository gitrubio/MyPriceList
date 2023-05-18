package com.example.mypricelist.ui.creation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypricelist.Adapters.ProductAdapter
import com.example.mypricelist.R
import com.example.mypricelist.models.ListModel
import com.example.mypricelist.models.ProductModel
import com.example.mypricelist.models.TypeProductModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class CreateProducts : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val coleccion: CollectionReference = db.collection("productos")
    private val listTypeProduct = listOf<TypeProductModel>(
        TypeProductModel("Bebidas"),
        TypeProductModel("Snaks"),
        TypeProductModel("Licor"),
        TypeProductModel("Dermocosmeticos"),
    )
    private val listProducts = (mutableListOf<ProductModel>())
    private var ListAdapter: ProductAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_products)
        val toolbar = findViewById<Toolbar>(R.id.toolbarProduct)
        setSupportActionBar(toolbar)
        // botón de retroceso en el Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // evento para el botón de retroceso
        toolbar.setNavigationOnClickListener { onBackPressed() }

        // se llenar el select de datos
        val spinner = findViewById<Spinner>(R.id.spiProducts)
        val data = listTypeProduct.map { it.nombre }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data)
        spinner.adapter = adapter

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
    fun saveList(view : View){
        var productName = findViewById<EditText>(R.id.edtListName)

        var correctly = controlErrors(productName)
        if(correctly){
            val controlSelectProduct = findViewById<Spinner>(R.id.spiProducts)
            val selectType = listTypeProduct[controlSelectProduct.selectedItemPosition]
            val producto = ProductModel(productName.text.toString(),"gramos", 1,selectType.nombre, R.drawable.candy)
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

        return correctlyData
    }
}