package com.example.mypricelist.ui.products

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypricelist.Adapters.ProductAdapter
import com.example.mypricelist.R
import com.example.mypricelist.databinding.FragmentDashboardBinding
import com.example.mypricelist.models.ProductModel
import com.example.mypricelist.ui.creation.CreateProducts
import com.example.mypricelist.ui.creation.data.remote.api.FirebaseAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashboardFragment : Fragment() {
    private val products :ArrayList<ProductModel> = ArrayList<ProductModel>()
    private var adapter: ProductAdapter?=null
    private var recycler: RecyclerView?=null
    private val firebaseAdapter = FirebaseAdapter()
    private var _binding: FragmentDashboardBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val addButton = root.findViewById<FloatingActionButton>(R.id.btnAddProduct)

        addButton.setOnClickListener {
            val createListView = Intent(root.context, CreateProducts::class.java)
            val options = ActivityOptions.makeCustomAnimation(root.context, R.drawable.slide_in_right, R.drawable.slide_out_left)
            startActivity(createListView, options.toBundle())
        }

        adapter = ProductAdapter(products,true)
        recycler = root.findViewById(R.id.recyclerListProducts)

        val layourManager:RecyclerView.LayoutManager = LinearLayoutManager(requireContext().applicationContext)

        recycler?.layoutManager = layourManager
        recycler?.itemAnimator = DefaultItemAnimator()
        recycler?.adapter = adapter


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        leerListadoProductos()
    }
    fun leerListadoProductos(){
//           firebaseAdapter.getLists(listadoList, adapter)
        firebaseAdapter.listeningProducts(products, adapter)
    }


}