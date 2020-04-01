package com.nike.urbandictionary.usecases

import com.nike.urbandictionary.app.RequestDictionaryEntries
import com.nike.urbandictionary.domain.DictionaryEntry
import com.nike.urbandictionary.domain.DictionaryEntryRepository
import com.nike.urbandictionary.responses.Responses

class RequestDictionaryEntriesImpl(
    private val dictionaryEntryRepository: DictionaryEntryRepository
) : RequestDictionaryEntries {
    override operator fun invoke(requestTerm: String): Responses<List<DictionaryEntry>> =
        dictionaryEntryRepository.requestDictionaryEntries(requestTerm)
}