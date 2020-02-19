package com.nike.urbandictionary.app.models

data class DictionaryEntryModel(
    val searchWord: String,
    val definition: String,
    val thumbsUp: Long,
    val authorLine: String,
    val examples: String,
    val thumbsDown: Long
)