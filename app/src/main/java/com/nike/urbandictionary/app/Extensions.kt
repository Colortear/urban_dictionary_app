package com.nike.urbandictionary.app

import android.view.View

const val FADE_TIME = 125L

fun String.removeBrackets() : String =
    replace("[", "").replace("]", "")

fun View.fadeTransition(view: View = this, action: (View) -> Unit = {} ) {
    animate().alpha(0f).setDuration(FADE_TIME).setListener(null).withEndAction {
        action(view)
        animate().alpha(1f).setDuration(FADE_TIME).setListener(null)
    }
}

private fun View.fade(value: Float) = animate().alpha(value).setDuration(FADE_TIME).setListener(null)

fun View.fadeOut() {
    fade(0F)
    gone()
}

fun View.fadeIn() {
    fade(1F)
    visible()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}