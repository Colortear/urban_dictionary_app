package com.nike.urbandictionary.data

import com.nike.urbandictionary.domain.DictionaryEntry
import com.nike.urbandictionary.domain.DictionaryEntryRepository
import com.nike.urbandictionary.responses.EmptyResponse
import com.nike.urbandictionary.responses.Failure
import com.nike.urbandictionary.responses.Responses
import com.nike.urbandictionary.responses.Success

class DictionaryEntryRepositoryImpl(
    private val dictionaryEntrySource: DictionaryEntrySource,
    private val voiceRecognition: VoiceRecognition
) : DictionaryEntryRepository {
    override fun requestDictionaryEntries(requestTerm: String): Responses<List<DictionaryEntry>> =
        dictionaryEntrySource.requestDictionaryEntries(requestTerm)

    override fun requestVoiceRecognition(): Responses<List<DictionaryEntry>> =
        when (val response = voiceRecognition.voiceRecognizer()) {
            is Failure -> Failure(response.reason)
            is EmptyResponse -> Failure("Nothing was said")
            is Success -> dictionaryEntrySource.requestDictionaryEntries(response.data)
        }
}