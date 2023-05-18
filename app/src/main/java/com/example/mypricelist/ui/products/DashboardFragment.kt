package com.example.mypricelist.ui.products

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mypricelist.R
import com.example.mypricelist.databinding.FragmentDashboardBinding
import com.example.mypricelist.ui.creation.CreateListActivity
import com.example.mypricelist.ui.creation.CreateProducts
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val addButton = root.findViewById<FloatingActionButton>(R.id.btnAddProduct)
        addButton.setOnClickListener {
            val createListView = Intent(root.context, CreateProducts::class.java)
            val options = ActivityOptions.makeCustomAnimation(root.context, R.drawable.slide_in_right, R.drawable.slide_out_left)
            startActivity(createListView, options.toBundle())
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}