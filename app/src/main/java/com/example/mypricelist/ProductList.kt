package com.example.mypricelist
import com.example.mypricelist.models.ProductModel

class ProductList(private var _id:String, private var _tituloNota: String = "", private var _cantProducts: String = "", private var _total : String = "", private var _products:  ArrayList<ProductModel>, private var precio : String = "" ) {

    var id: String
        get() = _id
        set(value) {
            _id = value
        }
    var tituloNota: String
        get() = _tituloNota
        set(value) {
            _tituloNota = value
        }

    var cantNota: String
        get() = _cantProducts
        set(value) {
            _cantProducts = value
        }

    var totalNota: String
        get() = _total
        set(value) {
            _total = value
        }

    var products: ArrayList<ProductModel>
        get() = _products
        set(value) {
            _products = value
        }

    var precioNota: String
        get() = precio
        set(value) {
            precio = value
        }

}
