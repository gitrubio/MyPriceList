package com.example.mypricelist

class ProductList(private var _tituloNota: String = "", private var _textoNota: String = "") {

    var tituloNota: String
        get() = _tituloNota
        set(value) {
            _tituloNota = value
        }

    var textoNota: String
        get() = _textoNota
        set(value) {
            _textoNota = value
        }

}
