package com.nike.urbandictionary

import com.nike.urbandictionary.app.retrieval.DictionaryEntrySourceImpl
import com.google.common.truth.Truth.*
import com.nike.urbandictionary.responses.EmptyResponse
import com.nike.urbandictionary.responses.Success
import org.json.JSONObject
import org.junit.Test

const val VALID_ENTRY = "wat"
const val INVALID_ENTRY = "asldkfjaslkfjsal;eikjf"
const val EMPTY_ENTRY = ""

class DictionaryEntrySourceImplUnitTest {

    private val dictionarySource = DictionaryEntrySourceImpl()

    @Test
    fun requestDictionaryEntries_ValidEntry_ReturnsSuccess() {
        val response = dictionarySource.requestDictionaryEntries(VALID_ENTRY)

        assertThat(response).isInstanceOf(Success::class.java)
    }

    @Test
    fun requestDictionaryEntries_InvalidEntry_ReturnsEmptyResponse() {
        val response = dictionarySource.requestDictionaryEntries(INVALID_ENTRY)

        assertThat(response).isInstanceOf(EmptyResponse::class.java)
    }

    @Test
    fun requestDictionaryEntries_EmptyEntry_ReturnsEmptyResponse() {
        val response = dictionarySource.requestDictionaryEntries(EMPTY_ENTRY)

        assertThat(response).isInstanceOf(EmptyResponse::class.java)
    }

    @Test
    fun map_EmptyJson_ReturnsEmptyList() {
        val json = JSONObject("{ list: [] }")

        assertThat(dictionarySource.map(json)).isEmpty()
    }
}