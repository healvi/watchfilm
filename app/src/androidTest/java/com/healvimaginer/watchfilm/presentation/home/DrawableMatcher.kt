package com.healvimaginer.watchfilm.presentation.home

import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class DrawableMatcher(resourceId: Int) : TypeSafeMatcher<View?>() {
    override fun matchesSafely(item: View?): Boolean {
        return false
    }
    fun withDrawable(resourceId: Int): Matcher<View?>? {
        return DrawableMatcher(resourceId)
    }
    override fun describeTo(description: Description?) {}
}