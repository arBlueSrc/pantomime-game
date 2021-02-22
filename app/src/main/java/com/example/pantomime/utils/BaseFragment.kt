package com.example.pantomime.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    fun showMessage (message : String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }
}