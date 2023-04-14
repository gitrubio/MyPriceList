package com.example.mypricelist.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mypricelist.ProductList
import com.example.mypricelist.R
import com.example.mypricelist.databinding.FragmentHomeBinding
import com.example.mypricelist.ui.creation.CreateListActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.CollectionReference

class HomeFragment : Fragment() {
    val db = FirebaseFirestore.getInstance()
    val coleccion: CollectionReference = db.collection("ListMain")
    private var _binding: FragmentHomeBinding? = null
    val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerListas)
    val addButton = view?.findViewById<FloatingActionButton>(R.id.btnAddList)
    private val listadoList:ArrayList<ProductList> = ArrayList<ProductList>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        leerListadoList()
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val addButton = root.findViewById<FloatingActionButton>(R.id.btnAddList)

        addButton.setOnClickListener {
            val createListView = Intent(root.context,CreateListActivity::class.java)
            startActivity(createListView)
        }


        return root
    }


    fun leerListadoList(){
        listadoList.clear()
        coleccion.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val nuevaList:ProductList = ProductList(""+document.getString("name"),"10")
                        listadoList.add(nuevaList)
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


