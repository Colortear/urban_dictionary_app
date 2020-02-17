package com.nike.urbandictionary.app.models

import java.time.LocalDate

// This would contain the data prepared to be displayed

data class DictionaryEntryModel(
    val searchWord: String,
    val definition: String,
    val thumbsUp: Long,
    val author: String,
    val creationDate: LocalDate,
    val examples: List<String>,
    val thumbsDown: Long
)