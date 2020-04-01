package com.nike.urbandictionary.app.viewmodels

import androidx.lifecycle.*

class DictionaryViewModelFactory(private val useCases: UseCases) : ViewModelProvider.Factory {

    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  = DictionaryViewModel(useCases) as T
}