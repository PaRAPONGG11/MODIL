package com.example.modil.ui.kalkulator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KalkulatorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Kalkulator Fragment"
    }
    val text: LiveData<String> = _text
}