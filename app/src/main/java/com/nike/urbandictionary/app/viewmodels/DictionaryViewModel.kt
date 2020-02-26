package com.nike.urbandictionary.app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nike.urbandictionary.app.models.DictionaryEntryModel
import com.nike.urbandictionary.app.RequestDictionaryEntries
import com.nike.urbandictionary.app.removeBrackets
import com.nike.urbandictionary.domain.DictionaryEntry
import com.nike.urbandictionary.responses.EmptyResponse
import com.nike.urbandictionary.responses.Failure
import com.nike.urbandictionary.responses.Responses
import com.nike.urbandictionary.responses.Success
import java.time.LocalDate

const val DATE_CHAR_COUNT = 10

class DictionaryViewModel(private val requestDictionaryEntries: RequestDictionaryEntries) : ViewModel() {

    var dictionaryEntries: MutableLiveData<List<DictionaryEntryModel>> = MutableLiveData()
    private var orderDescendingLikes = true

    fun getDictionaryEntries(searchTerm: String) : Responses<String> =
        requestDictionaryEntries.invoke(searchTerm).let { response ->
            when (response) {
                is EmptyResponse -> {
                    dictionaryEntries.value = emptyList()
                    EmptyResponse()
                }
                is Failure -> {
                    dictionaryEntries.value = emptyList()
                    handleErrors(response.reason)
                }
                is Success -> {
                    dictionaryEntries.value = response.data.map { entry ->
                        transformDictionaryEntry(entry)
                    }.sortListByLikes(orderDescendingLikes)
                    Success("")
                }
            }
        }

    fun onFilterButtonClick() {
        orderDescendingLikes = !orderDescendingLikes
        dictionaryEntries.value = dictionaryEntries.value?.sortListByLikes(orderDescendingLikes)
    }

    fun isOrderedAscending() = orderDescendingLikes

    private fun List<DictionaryEntryModel>.sortListByLikes(sortLikesAscending: Boolean): List<DictionaryEntryModel> =
        toMutableList().sortedByDescending {
            if (sortLikesAscending) it.thumbsUp else it.thumbsDown
        }

    private fun handleErrors(msg: String) : Failure<String> = Failure(msg)

    private fun transformDictionaryEntry(entry: DictionaryEntry) : DictionaryEntryModel {
        val authorLine = prepareAuthorLine(
            entry.author,
            LocalDate.parse(entry.creationDate.substring(0 until DATE_CHAR_COUNT)).toString()
        )

        return entry.run {
            DictionaryEntryModel(
                searchWord,
                definition.removeBrackets(),
                thumbsUp,
                authorLine,
                examples.removeBrackets(),
                thumbsDown
            )
        }
    }

    private fun prepareAuthorLine(author: String, date: String) : String = "by $author on $date"
}