package com.nike.urbandictionary.app.ui

import android.view.View

interface ListDisplayManager {
    var view: View

    @Throws(Exception::class)
    fun replaceCurrentView(replaceWith: View)
}