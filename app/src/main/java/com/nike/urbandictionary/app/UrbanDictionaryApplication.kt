package com.nike.urbandictionary.app

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent

class UrbanDictionaryApplication : Application() {

    val appContainer = AppContainer()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onEnd() {

    }
}