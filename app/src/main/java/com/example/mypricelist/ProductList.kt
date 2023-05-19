package com.example.mypricelist

class ProductList(private var _id:String, private var _tituloNota: String = "", private var _cantProducts: String = "", private var _total : String = "") {

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

}
