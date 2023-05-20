package com.example.mypricelist.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mypricelist.R
import com.example.mypricelist.models.TypeProductModel

class utils {


}
fun resourByCategori(category: String ) : Int {
    when (category){
        "Bebidas" ->{
            return R.id.bottle
        }
        "Snaks Dulce" ->{
            return R.id.candy
        }
        "Snaks Salado" ->{
            return R.id.chips
        }
        "Licor" ->{
            return R.id.liqor
        }
        "Dermocosmeticos" ->{
            return R.id.face
        }
        "Cuidado personal" ->{
            return R.id.healt
        }
        "Aseo" ->{
            return R.id.clean
        }
        "Granos" ->{
            return R.id.grain
        }
        "Aceites" ->{
            return R.id.oil
        }
        else -> {
            return R.id.candy
        }
    }
}
class SinEspaciadoItemDecoration(private val espacio: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // Establecer márgenes a 0 en todos los lados para cada elemento del RecyclerView
        outRect.set(0, 0, 0, 0)
    }
}