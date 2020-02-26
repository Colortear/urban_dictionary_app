package com.nike.urbandictionary.app.activities

import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nike.urbandictionary.R
import com.nike.urbandictionary.app.AppContainer
import com.nike.urbandictionary.app.UrbanDictionaryApplication
import com.nike.urbandictionary.app.adapters.DictionaryEntryRecyclerAdapter
import com.nike.urbandictionary.app.fadeTransition
import com.nike.urbandictionary.app.models.DictionaryEntryModel
import com.nike.urbandictionary.app.ui.DictionaryListViewManager
import com.nike.urbandictionary.app.ui.ListDisplayManager
import com.nike.urbandictionary.app.viewmodels.DictionaryViewModel
import com.nike.urbandictionary.app.viewmodels.DictionaryViewModelFactory
import com.nike.urbandictionary.responses.EmptyResponse
import com.nike.urbandictionary.responses.Failure
import com.nike.urbandictionary.responses.Responses
import com.nike.urbandictionary.responses.Success
import kotlinx.android.synthetic.main.main_layout.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val appContainer = (application as UrbanDictionaryApplication?)?.appContainer ?: AppContainer()
    private val viewModel: DictionaryViewModel by lazy {
        val factory = DictionaryViewModelFactory(application, appContainer.requestDictionaryEntries)

        ViewModelProvider(this, factory).get(DictionaryViewModel::class.java)
    }
    private val listAdapter: DictionaryEntryRecyclerAdapter by lazy {
        DictionaryEntryRecyclerAdapter(viewModel.dictionaryEntries)
    }
    private val recyclerLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }
    private val observer = Observer<List<DictionaryEntryModel>> {
        listDisplayManager.replaceCurrentView(item_list)
        listAdapter.notifyDataSetChanged()
    }
    lateinit var listDisplayManager: ListDisplayManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.main_layout)
        viewModel.dictionaryEntries.observe(this, observer)
        listDisplayManager = DictionaryListViewManager(empty_list_message)
        search_bar.setOnQueryTextListener(createSearchQueryListener(viewModel))
        filter_button.run {
            setOnClickListener(createFilterClickListener(viewModel))
            setImageResource(R.drawable.like)
            scaleType = ImageView.ScaleType.FIT_CENTER
            adjustViewBounds = true
        }
        item_list.run {
            layoutManager = recyclerLayoutManager
            adapter = listAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun createFilterClickListener(vm: DictionaryViewModel) = View.OnClickListener {
        vm.onFilterButtonClick()
        filter_button.fadeTransition { (it as ImageButton).setImageResource(getThumbDrawable()) }
    }

    private fun getThumbDrawable() =
        if (viewModel.isOrderedAscending()) R.drawable.like else R.drawable.dislike

    private fun createSearchQueryListener(vm: DictionaryViewModel) : SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean = false
            override fun onQueryTextSubmit(query: String?): Boolean {
                launch {
                    handleResponseForUI(vm.getDictionaryEntries(query ?: ""))
                }
                listDisplayManager.replaceCurrentView(progress_bar)
                return false
            }
        }
    }

    private fun handleResponseForUI(response: Responses<String>) {
        when (response) {
            is EmptyResponse -> displayEmptyViewWithMessage("No results for search.")
            is Failure -> displayEmptyViewWithMessage(response.reason)
            is Success -> listDisplayManager.replaceCurrentView(item_list)
        }
    }

    private fun displayEmptyViewWithMessage(msg: String) {
        empty_list_message.text = msg
        listDisplayManager.replaceCurrentView(empty_list_message)
    }
}