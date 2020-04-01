package com.nike.urbandictionary.app.retrieval.voicerecognition

import android.speech.SpeechRecognizer
import com.nike.urbandictionary.app.UrbanDictionaryApplication
import com.nike.urbandictionary.data.VoiceRecognition
import com.nike.urbandictionary.responses.Failure
import com.nike.urbandictionary.responses.Responses
import com.nike.urbandictionary.responses.Success

class VoiceRecognitionImpl(private val application: UrbanDictionaryApplication) :
    VoiceRecognition {
    private val isAvailable: Boolean
        get() = SpeechRecognizer.isRecognitionAvailable(application)
    private val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(application).apply {
        setRecognitionListener(application.appContainer.voiceRecognitionListener)
    }

    override fun voiceRecognizer(): Responses<String> =
        if (isAvailable)
            Failure("Speech recognition is unavailable on this device")
        else
            Success(getSpeechToText())

    private fun getSpeechToText() : String {
        TODO("Fill in") // TODO: Fill in
    }
}