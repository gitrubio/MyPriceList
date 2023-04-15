package com.example.mypricelist.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class utils {
}

class SinEspaciadoItemDecoration(private val espacio: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // Establecer márgenes a 0 en todos los lados para cada elemento del RecyclerView
        outRect.set(0, 0, 0, 0)
    }
}