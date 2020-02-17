package com.nike.urbandictionary.app

import com.nike.urbandictionary.app.retrieval.DictionaryEntrySourceImpl
import com.nike.urbandictionary.data.DictionaryEntryRepositoryImpl
import com.nike.urbandictionary.usecases.RequestDictionaryEntriesImpl

class AppContainer {

    private val dictionaryEntrySource = DictionaryEntrySourceImpl()
    private val dictionaryEntryRepository = DictionaryEntryRepositoryImpl(dictionaryEntrySource)

    val requestDictionaryEntries = RequestDictionaryEntriesImpl(dictionaryEntryRepository)
}