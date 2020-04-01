package com.nike.urbandictionary.usecases

import com.nike.urbandictionary.app.RequestVoiceRecognition
import com.nike.urbandictionary.domain.DictionaryEntry
import com.nike.urbandictionary.domain.DictionaryEntryRepository
import com.nike.urbandictionary.responses.Responses

class RequestVoiceRecognitionImpl(
    private val dictionaryEntryRepository: DictionaryEntryRepository
) : RequestVoiceRecognition {
    override fun invoke(): Responses<List<DictionaryEntry>> =
        dictionaryEntryRepository.requestVoiceRecognition()
}