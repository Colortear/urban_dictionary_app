package com.nike.urbandictionary.app.retrieval

import android.util.Log
import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import com.nike.urbandictionary.*
import com.nike.urbandictionary.data.DictionaryEntrySource
import com.nike.urbandictionary.domain.DictionaryEntry
import com.nike.urbandictionary.responses.Failure
import com.nike.urbandictionary.responses.EmptyResponse
import com.nike.urbandictionary.responses.Responses
import com.nike.urbandictionary.responses.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.lang.Exception
import javax.net.ssl.HttpsURLConnection

const val TIMEOUT = 5000
const val REQUEST = "GET"

class DictionaryEntrySourceImpl : DictionaryEntrySource {

    override fun requestDictionaryEntries(requestTerm: String): Responses<List<DictionaryEntry>> = runBlocking {
        val headers = listOf(
            listOf(HOST_HEADER_CONTENT, HOST_HEADER_TITLE),
            listOf(KEY_HEADER_TITLE, KEY_HEADER_CONTENT)
        )
        var connection: HttpsURLConnection? = null

        if (requestTerm.isBlank())
            return@runBlocking EmptyResponse<List<DictionaryEntry>>()
        withContext(Dispatchers.IO) {
            try {
                connection = getHttpsRequest(buildRequestUrl(requestTerm), TIMEOUT, REQUEST, headers)
                connection?.inputStream?.bufferedReader().use { it?.readText() }?.let {
                    Success(map(JSONObject(it))).takeIf { ret ->
                        ret.data.isNotEmpty()
                    } ?: EmptyResponse<List<DictionaryEntry>>()
                } ?: throw IOException()
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage(
                    when (e) {
                        is IOException, is UnsupportedEncodingException -> Some(e.localizedMessage)
                        else -> None
                    }
                )
                Failure<List<DictionaryEntry>>(connection?.responseMessage ?: "unknown error")
            } finally {
                connection?.inputStream?.close()
                connection?.disconnect()
            }
        }
    }

    private fun errorMessage(msg: Option<String>) =
        Log.e("ERROR",
            when (msg) {
                is Some -> msg.t
                is None -> "Unspecified error"
            }
        )

    override fun buildRequestUrl(requestTerm: String): String = "$API_URL$requestTerm"

    @Throws(JSONException::class)
    override fun map(json: JSONObject): List<DictionaryEntry> {
        val itemList = json.getJSONArray("list") ?: JSONArray()
        val listSize = itemList.length()

        return List(listSize) { idx ->
            itemList.getJSONObject(idx).run {
                DictionaryEntry(
                    getString("word"),
                    getString("definition"),
                    getLong("thumbs_up"),
                    getString("author"),
                    getString("written_on"),
                    getString("example"),
                    getLong("thumbs_down")
                )
            }
        }
    }
}