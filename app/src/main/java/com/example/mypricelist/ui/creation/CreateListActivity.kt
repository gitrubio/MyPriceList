package com.example.mypricelist.ui.creation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.mypricelist.R
import com.example.mypricelist.models.ListModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class CreateListActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    val coleccion: CollectionReference = db.collection("ListMain")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_list)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Habilita el botón de retroceso en el Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Establece el manejador de eventos para el botón de retroceso
        toolbar.setNavigationOnClickListener { onBackPressed() }

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

    fun saveList(){
        var productos : List<String> = listOf("juan","carlos","lucho")
        val producto = ListModel("carlos",12.3, productos)
        coleccion.add(producto)
    }
}