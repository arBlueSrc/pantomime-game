package com.example.pantomime.di

import com.example.pantomime.localdb.provideSharedPreferences
import com.example.pantomime.ui.gamepage.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ApplicationModule = module {
    single { provideSharedPreferences(get()) }
}

val viewModelModules = module {
    viewModel{GameViewModel()}
}