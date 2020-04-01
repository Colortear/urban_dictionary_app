package com.nike.urbandictionary.app

import com.nike.urbandictionary.domain.DictionaryEntry
import com.nike.urbandictionary.responses.Responses

interface RequestVoiceRecognition {
    operator fun invoke() : Responses<List<DictionaryEntry>>
}