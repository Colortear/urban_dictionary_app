package com.nike.urbandictionary.app

import com.nike.urbandictionary.app.listeners.VoiceRecognitionListenerImpl
import com.nike.urbandictionary.app.retrieval.DictionaryEntrySourceImpl
import com.nike.urbandictionary.app.retrieval.voicerecognition.VoiceRecognitionImpl
import com.nike.urbandictionary.app.retrieval.voicerecognition.VoiceRecognitionListener
import com.nike.urbandictionary.data.DictionaryEntryRepositoryImpl
import com.nike.urbandictionary.data.DictionaryEntrySource
import com.nike.urbandictionary.data.VoiceRecognition
import com.nike.urbandictionary.domain.DictionaryEntryRepository
import com.nike.urbandictionary.usecases.RequestDictionaryEntriesImpl
import com.nike.urbandictionary.usecases.RequestVoiceRecognitionImpl

class AppContainer(application: UrbanDictionaryApplication) {

    private val voiceRecognition: VoiceRecognition = VoiceRecognitionImpl(application)
    private val dictionaryEntrySource: DictionaryEntrySource = DictionaryEntrySourceImpl()
    private val dictionaryEntryRepository: DictionaryEntryRepository =
        DictionaryEntryRepositoryImpl(dictionaryEntrySource, voiceRecognition)

    val voiceRecognitionListener: VoiceRecognitionListener = VoiceRecognitionListenerImpl()
    val requestDictionaryEntries: RequestDictionaryEntries = RequestDictionaryEntriesImpl(dictionaryEntryRepository)
    val requestVoiceRecognition: RequestVoiceRecognition = RequestVoiceRecognitionImpl(dictionaryEntryRepository)
}