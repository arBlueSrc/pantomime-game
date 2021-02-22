package com.example.pantomime.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val lastScore = MutableLiveData(0)

}