package com.nike.urbandictionary.app.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.nike.urbandictionary.R
import com.nike.urbandictionary.app.AppContainer
import com.nike.urbandictionary.app.UrbanDictionaryApplication
import com.nike.urbandictionary.app.viewmodels.DictionaryViewModel

class MainActivity : AppCompatActivity() {

    private val appContainer = (application as UrbanDictionaryApplication).appContainer
    private lateinit var viewModel: DictionaryViewModel

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.main_layout)
        viewModel = DictionaryViewModel(application, appContainer.requestDictionaryEntries)
    }
}