package com.nike.urbandictionary.data

import com.nike.urbandictionary.responses.Responses

interface VoiceRecognition {
    fun voiceRecognizer(): Responses<String>
}