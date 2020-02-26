package com.nike.urbandictionary.app.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.nike.urbandictionary.app.RequestDictionaryEntries

class DictionaryViewModelFactory(
    private val application: Application,
    private val requestDictionaryEntries: RequestDictionaryEntries
) : ViewModelProvider.AndroidViewModelFactory(application) {

    fun create(modelClass: Class<DictionaryViewModel>): DictionaryViewModel {
        return DictionaryViewModel(application, requestDictionaryEntries)
    }
}