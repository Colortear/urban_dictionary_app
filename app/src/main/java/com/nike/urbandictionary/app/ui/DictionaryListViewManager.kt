package com.nike.urbandictionary.app.ui

import android.view.View
import com.nike.urbandictionary.app.fadeIn
import com.nike.urbandictionary.app.fadeOut

class DictionaryListViewManager(initialView: View): ListDisplayManager {

    override var view = initialView

    override fun replaceCurrentView(replaceWith: View) {
        view.fadeOut()
        if (replaceWith.parent != view.parent)
            throw (Exception("view parents do not match."))
        replaceWith.fadeIn()
        view = replaceWith
    }
}