package com.nike.urbandictionary.app.viewmodels

import androidx.lifecycle.*
import com.nike.urbandictionary.app.RequestDictionaryEntries

class DictionaryViewModelFactory(
    private val requestDictionaryEntries: RequestDictionaryEntries
) : ViewModelProvider.Factory {

    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DictionaryViewModel(requestDictionaryEntries) as T
    }
}