package com.nike.urbandictionary.data

import com.nike.urbandictionary.domain.DictionaryEntry
import com.nike.urbandictionary.responses.Responses
import org.json.JSONObject

interface DictionaryEntrySource {
    fun getDictionaryEntries() : Responses<List<DictionaryEntry>>
    fun requestDictionaryEntries(requestTerm: String) : Responses<List<DictionaryEntry>>
    fun buildRequestUrl(requestTerm: String) : String
    fun map(json: JSONObject) : List<DictionaryEntry>
}