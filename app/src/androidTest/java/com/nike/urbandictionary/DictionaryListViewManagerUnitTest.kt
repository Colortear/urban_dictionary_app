package com.nike.urbandictionary

import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import com.nike.urbandictionary.app.activities.MainActivity
import com.nike.urbandictionary.app.ui.DictionaryListViewManager
import com.nike.urbandictionary.app.ui.ListDisplayManager
import kotlinx.android.synthetic.main.main_layout.*
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
class DictionaryListViewManagerUnitTest {
    private lateinit var viewManager: ListDisplayManager

    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun createViewManager() {
        viewManager = DictionaryListViewManager(activityRule.activity.empty_list_message)
    }
     @Test
     fun replaceCurrentView_RaceConditionTest() : Unit = runBlocking {
         withContext(Dispatchers.Main) {
             viewManager.run {
                 replaceCurrentView(activityRule.activity.progress_bar)
                 replaceCurrentView(activityRule.activity.item_list)
                 replaceCurrentView(activityRule.activity.empty_list_message)
                 launch { replaceCurrentView(activityRule.activity.item_list) }
                 launch { replaceCurrentView(activityRule.activity.empty_list_message) }
                 launch { replaceCurrentView(activityRule.activity.progress_bar) }
                 Unit
             }
         }
     }
}