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
import com.example.mypricelist.models.UnitProductModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class CreateProducts : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val coleccion: CollectionReference = db.collection("productos")
    private val listTypeProduct = listOf<TypeProductModel>(
        TypeProductModel("Bebidas"),
        TypeProductModel("Snaks Dulce"),
        TypeProductModel("Snaks Salado"),
        TypeProductModel("Licor"),
        TypeProductModel("Dermocosmeticos"),
        TypeProductModel("Cuidado personal"),
        TypeProductModel("Aseo"),
        TypeProductModel("Granos"),
        TypeProductModel("Aceites"),
    )
    private val listUnidades = listOf<UnitProductModel>(
        UnitProductModel("Gramos", "gr"),
        UnitProductModel("Mililitros","ml"),
        UnitProductModel("Unidad","u"),
        UnitProductModel("Litro","l"),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_products)
        val toolbar = findViewById<Toolbar>(R.id.toolbarProduct)
        setSupportActionBar(toolbar)
        // botón de retroceso en el Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // evento para el botón de retroceso
        toolbar.setNavigationOnClickListener { onBackPressed() }

        // se llena el select de tipos de productos
        val spinner = findViewById<Spinner>(R.id.spiProducts)
        val data = listTypeProduct.map { it.nombre }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data)
        spinner.adapter = adapter


        // se llena el select de unidades
        val spinnerUnits = findViewById<Spinner>(R.id.spiUnits)
        val dataUnits = listUnidades.map { it.name }
        val adapterUnits = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dataUnits)
        spinnerUnits.adapter = adapterUnits
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
        var productPrice = findViewById<EditText>(R.id.textPrecio)
        var correctly = controlErrors(productName,productPrice)
        if(correctly){
            val controlSelectProduct = findViewById<Spinner>(R.id.spiProducts)
            val controlSelectUnits = findViewById<Spinner>(R.id.spiUnits)
            val selectType = listTypeProduct[controlSelectProduct.selectedItemPosition]
            val selectUnit = listUnidades[controlSelectUnits.selectedItemPosition]
            val producto = ProductModel(productName.text.toString(),selectUnit.name, 1,selectType.nombre,10101,productPrice.text.toString().toInt())
            coleccion.add(producto)
            finishAfterTransition()
        }
    }

    private fun controlErrors(listName : EditText, price : EditText): Boolean {
        var correctlyData = true
        var nameList = listName.text.toString()
        if(nameList.isEmpty()){
            listName.error = "Por favor Ingrese un nombre para el producto"
            correctlyData = false
        }
        if(price.text.isEmpty()){
            price.error = "Por favor Ingrese un precio para el producto"
            correctlyData = false
        }
        return correctlyData
    }
}