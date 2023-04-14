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
import com.example.mypricelist.R
import com.example.mypricelist.databinding.FragmentHomeBinding
import com.example.mypricelist.ui.creation.CreateListActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerListas)
    val addButton = view?.findViewById<FloatingActionButton>(R.id.btnAddList)

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}