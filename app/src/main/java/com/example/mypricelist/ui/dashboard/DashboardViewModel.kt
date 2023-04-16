package com.example.mypricelist.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "DISPONIBLE EN UN FUTURO"
    }
    val text: LiveData<String> = _text
}