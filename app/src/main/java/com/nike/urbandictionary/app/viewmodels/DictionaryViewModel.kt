package com.nike.urbandictionary.app.viewmodels

import android.app.Application
import android.view.View
import android.widget.SearchView
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

    var dictionaryEntries: MutableLiveData<List<DictionaryEntryModel>> = MutableLiveData()
    private var orderAscendingLikes = true

    fun getDictionaryEntries(searchTerm: String) : Responses<String> =
        requestDictionaryEntries.invoke(searchTerm).let { response ->
            when (response) {
                is EmptyResponse -> EmptyResponse()
                is Failure -> handleErrors(response.reason)
                is Success -> {
                    dictionaryEntries.value = response.data.map { entry -> transformDictionaryEntry(entry) }
                    Success("")
                }
            }
        }

    fun onFilterButtonClick() {
        orderAscendingLikes = !orderAscendingLikes
        sortListByLikes(orderAscendingLikes)
    }

    private fun sortListByLikes(sortLikesAscending: Boolean) =
        dictionaryEntries.value?.sortedBy {
            if (sortLikesAscending) it.thumbsUp else it.thumbsDown
        }

    private fun handleErrors(msg: String) : Failure<String> {
        return Failure("")
    }

    private fun transformDictionaryEntry(entry: DictionaryEntry) : DictionaryEntryModel {
        val authorLine = prepareAuthorLine(
            entry.author,
            LocalDate.parse(entry.creationDate.substring(0 until DATE_RANGE)).toString()
        )

        return entry.run {
            DictionaryEntryModel(
                searchWord,
                definition.replace("[", "").replace("]", ""),
                thumbsUp.toString(),
                authorLine,
                examples,
                thumbsDown.toString()
            )
        }
    }

    private fun prepareAuthorLine(author: String, date: String) : String = "by $author on $date"
}