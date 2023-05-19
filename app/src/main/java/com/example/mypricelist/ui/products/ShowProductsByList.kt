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
import com.google.firebase.firestore.ListenerRegistration

class ShowProductsByList : AppCompatActivity() {
    private val firebaseAdapter = FirebaseAdapter()
    private val products :ArrayList<ProductModel> = ArrayList<ProductModel>()
    private var adapter: ProductAdapter?=null
    private var listener: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_products_by_list)
        val toolbar = findViewById<Toolbar>(R.id.toolbarProductByList)
        setSupportActionBar(toolbar)
        // botón de retroceso en el Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // evento para el botón de retroceso
        toolbar.setNavigationOnClickListener { onBackPressed() }


        val recyclerView: RecyclerView = findViewById(R.id.recyclerListProductsByList)
        // instancia del adaptador
        adapter = ProductAdapter(products)

        val extras = intent.extras
        if (extras != null) {
            val productos = extras.getString("id")
        }


        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager




    }

    override fun onDestroy() {
        super.onDestroy()
        listener?.remove()
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


    override fun onStart() {
        super.onStart()
        leerListadoProductos()
    }
    fun leerListadoProductos(){
        val extras = intent.extras
        if (extras != null) {
            val listId = extras.getString("id") ?: ""


        listener = firebaseAdapter.listeningProductsByList(products, listId, adapter)
        }
    }


}


