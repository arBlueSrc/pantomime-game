package com.example.pantomime.localdb

import android.content.Context
import android.content.SharedPreferences

fun provideSharedPreferences(context : Context) : SharedPreferences{
   return context.getSharedPreferences("PantomimApp",Context.MODE_PRIVATE)
}