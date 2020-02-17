package com.nike.urbandictionary.domain

data class DictionaryEntry(
    val searchWord: String,
    val definition: String,
    val thumbsUp: Long,
    val author: String,
    val creationDate: String,
    val examples: String,
    val thumbsDown: Long
)