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
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypricelist.Adapters.ProductAdapter
import com.example.mypricelist.models.ProductModel

class CreateListActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    val coleccion: CollectionReference = db.collection("ListMain")
    val data = listOf("Papitas", "Coca Cola", "Cerveza","Chocolate","Wisky")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_list)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Habilita el botón de retroceso en el Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Establece el manejador de eventos para el botón de retroceso
        toolbar.setNavigationOnClickListener { onBackPressed() }
        val spinner = findViewById<Spinner>(R.id.spiProducts)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data)
        spinner.adapter = adapter

        // En tu actividad o fragmento

        // Obtén una referencia al RecyclerView en tu diseño de vista
            val recyclerView: RecyclerView = findViewById(R.id.ReView)
        val listaObjetos = listOf(
            ProductModel("Objeto 1", "Unidad 1", 5, "Tipo 1"),
            ProductModel("Objeto 2", "Unidad 2", 10, "Tipo 2"),
            ProductModel("Objeto 3", "Unidad 3", 3, "Tipo 1"),
            ProductModel("Objeto 4", "Unidad 1", 7, "Tipo 2"),
            ProductModel("Objeto 5", "Unidad 2", 2, "Tipo 1")
        )
        // Crea una instancia de tu adaptador, pasándole la lista de objetos como parámetro
            val objetoAdapter = ProductAdapter(listaObjetos)

        // Asigna el adaptador al RecyclerView
            recyclerView.adapter = objetoAdapter

        // Opcional: Configura el diseño de vista para el RecyclerView (por ejemplo, LinearLayoutManager, GridLayoutManager, etc.)
            val layoutManager = LinearLayoutManager(this) // Puedes cambiar "this" con la referencia a tu actividad o fragmento
            recyclerView.layoutManager = layoutManager



    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.toolbar_nav_menu, menu) // Infla el menú en la Toolbar
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun saveList(view : View){
        var controListName = findViewById<EditText>(R.id.edtListName)
        var controlSelectProduct = findViewById<Spinner>(R.id.spiProducts)

        controlErrors(controListName,controlSelectProduct)
        var product = data[controlSelectProduct.selectedItemPosition]
        var productos : List<String> = listOf(product)
        val producto = ListModel(controListName.text.toString(),12.3, productos)
        coleccion.add(producto)
    }

    private fun controlErrors(listName : EditText, Productos : Spinner): Boolean {
        var correctlyData : Boolean = true
        var nameList = listName.text.toString()
        if(nameList.isEmpty()){
            listName.error = "Por favor Ingrese un nombre para la lista"
            correctlyData = false
        }
        return correctlyData
    }
}