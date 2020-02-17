package com.nike.urbandictionary.data

import com.nike.urbandictionary.domain.DictionaryEntry
import com.nike.urbandictionary.domain.DictionaryEntryRepository
import com.nike.urbandictionary.responses.Responses

class DictionaryEntryRepositoryImpl(
    private val dictionaryEntrySource: DictionaryEntrySource
) : DictionaryEntryRepository {
    override fun requestDictionaryEntries(requestTerm: String): Responses<List<DictionaryEntry>> =
        dictionaryEntrySource.requestDictionaryEntries(requestTerm)
}