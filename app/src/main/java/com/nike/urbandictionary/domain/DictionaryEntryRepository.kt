package com.nike.urbandictionary.domain

import com.nike.urbandictionary.responses.Responses

interface DictionaryEntryRepository {
    fun requestDictionaryEntries(requestTerm: String) : Responses<List<DictionaryEntry>>
}