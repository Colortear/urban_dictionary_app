package com.nike.urbandictionary.app.retrieval.voicerecognition

import android.speech.RecognitionListener

interface VoiceRecognitionListener : RecognitionListener {
    var speechResults: List<String>
}