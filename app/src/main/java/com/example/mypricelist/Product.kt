package com.example.mypricelist

class Product(private var _id:String, private var _name: String = "", private var _tipo: String = "", private var _unidad: String = "") {

    var id: String
        get() = _id
        set(value) {
            _id = value
        }
    var tipo: String
        get() = _tipo
        set(value) {
            _tipo = value
        }

    var unidad: String
        get() = _unidad
        set(value) {
            _unidad = value
        }

}
