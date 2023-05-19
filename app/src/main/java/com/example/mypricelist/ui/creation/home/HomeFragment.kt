package com.example.mypricelist.ui.creation.home

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EdgeEffect
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBindings
import com.example.mypricelist.AdaptadorProductList
import com.example.mypricelist.ProductList
import com.example.mypricelist.R
import com.example.mypricelist.ui.creation.data.remote.api.FirebaseAdapter
import com.example.mypricelist.databinding.FragmentHomeBinding
import com.example.mypricelist.ui.creation.CreateListActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.CollectionReference

class HomeFragment : Fragment() {
    val db = FirebaseFirestore.getInstance()
    val coleccion: CollectionReference = db.collection("ListMain")
    private var _binding: FragmentHomeBinding? = null
    private val listadoList:ArrayList<ProductList> = ArrayList<ProductList>()
    private val binding get() = _binding!!
    private var adapter:AdaptadorProductList?=null
    private var recycler: RecyclerView?=null
    private val firebaseAdapter = FirebaseAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val addButton = root.findViewById<FloatingActionButton>(R.id.btnAddList)

        addButton.setOnClickListener {
            val createListView = Intent(root.context,CreateListActivity::class.java)
            val options = ActivityOptions.makeCustomAnimation(root.context, R.drawable.slide_in_right, R.drawable.slide_out_left)
            startActivity(createListView, options.toBundle())
        }

        adapter = AdaptadorProductList(listadoList, requireContext().applicationContext)
        recycler = root.findViewById(R.id.recyclerListas)

        val layourManager:RecyclerView.LayoutManager = LinearLayoutManager(requireContext().applicationContext)

        recycler?.layoutManager = layourManager
        recycler?.itemAnimator = DefaultItemAnimator()
        recycler?.adapter = adapter

        return root
    }

    override fun onStart() {
        super.onStart()
        leerListadoList()
    }
    fun leerListadoList(){
//           firebaseAdapter.getLists(listadoList, adapter)
           firebaseAdapter.listeningList(listadoList, adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
