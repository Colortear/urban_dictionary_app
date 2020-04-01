package com.nike.urbandictionary.app.listeners

import android.os.Bundle
import com.nike.urbandictionary.app.retrieval.voicerecognition.VoiceRecognitionListener

class VoiceRecognitionListenerImpl : VoiceRecognitionListener {
    override var speechResults = emptyList<String>()

    override fun onBeginningOfSpeech() {}
    override fun onBufferReceived(buffer: ByteArray?) {}
    override fun onEndOfSpeech() {}
    override fun onError(error: Int) { }
    override fun onEvent(eventType: Int, params: Bundle?) {}
    override fun onPartialResults(partialResults: Bundle?) {}
    override fun onReadyForSpeech(params: Bundle?) {}
    override fun onRmsChanged(rmsdB: Float) {}
    override fun onResults(results: Bundle?) {
        //TODO implement this
    }
}