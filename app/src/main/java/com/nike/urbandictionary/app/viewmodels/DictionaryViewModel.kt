package com.nike.urbandictionary.app.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nike.urbandictionary.app.models.DictionaryEntryModel
import com.nike.urbandictionary.app.RequestDictionaryEntries
import com.nike.urbandictionary.domain.DictionaryEntry
import com.nike.urbandictionary.responses.EmptyResponse
import com.nike.urbandictionary.responses.Failure
import com.nike.urbandictionary.responses.Responses
import com.nike.urbandictionary.responses.Success
import java.time.LocalDate

const val DATE_RANGE = 10

class DictionaryViewModel(
    application: Application,
    private val requestDictionaryEntries: RequestDictionaryEntries
) : AndroidViewModel(application) {

    var dictionaryEntryModels: MutableLiveData<List<DictionaryEntryModel>> = MutableLiveData()

    fun getDictionaryEntries(searchTerm: String) : Responses<String> =
        requestDictionaryEntries.invoke(searchTerm).let { response ->
            when (response) {
                is EmptyResponse -> EmptyResponse()
                is Failure -> handleErrors(response.reason)
                is Success -> {
                    dictionaryEntryModels.value = response.data.map {
                            entry -> transformDictionaryEntry(entry)
                    }
                    Success("")
                }
            }
        }

    private fun handleErrors(msg: String) : Failure<String> {
        return Failure("")
    }

    private fun transformDictionaryEntry(entry: DictionaryEntry) : DictionaryEntryModel =
        entry.run {
            DictionaryEntryModel(
                searchWord,
                definition.replace("[", "").replace("]", ""),
                thumbsUp,
                author,
                LocalDate.parse(creationDate.substring(0 until DATE_RANGE)),
                examples.split("\n\n"),
                thumbsDown
            )
        }
}