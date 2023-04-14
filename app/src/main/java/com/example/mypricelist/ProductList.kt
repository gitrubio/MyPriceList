package com.example.mypricelist

class ProductList(private var _tituloNota: String = "", private var _cantProducts: String = "") {

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

}
