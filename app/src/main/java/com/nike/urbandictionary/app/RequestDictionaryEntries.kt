package com.nike.urbandictionary.app

import com.nike.urbandictionary.domain.DictionaryEntry
import com.nike.urbandictionary.responses.Responses

interface RequestDictionaryEntries {
    operator fun invoke(requestTerm: String) : Responses<List<DictionaryEntry>>
}