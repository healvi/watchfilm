package com.healvimaginer.watchfilm.presentation.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.healvimaginer.watchfilm.R
import com.healvimaginer.watchfilm.domain.utils.DataDummy
import com.healvimaginer.watchfilm.domain.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val dummyFilm = DataDummy.generateDummyFilm()
    private val dummyTv = DataDummy.generateDummyTv()


    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadFilm() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_film))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_film))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyFilm.size))
    }

    @Test
    fun loadDetailFilm() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_film)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.title_film))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.title_film))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyFilm[0].title)))
        Espresso.onView(ViewMatchers.withId(R.id.rilis_film))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rilis_film))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyFilm[0].release_date)))
//        insert
        Espresso.onView(ViewMatchers.withId(R.id.add_favorite)).perform(ViewActions.click())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        delete
        Espresso.onView(ViewMatchers.withId(R.id.add_favorite)).perform(ViewActions.click())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadTv() {
        Espresso.onView(ViewMatchers.withText("Tv Shows")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))
    }

    @Test
    fun loadDetailTv() {
        Espresso.onView(ViewMatchers.withText("Tv Shows")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.title_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.title_tv))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTv[0].title)))
        Espresso.onView(ViewMatchers.withId(R.id.rilis_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rilis_tv))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTv[0].release_date)))
//        insert
        Espresso.onView(ViewMatchers.withId(R.id.add_favorite)).perform(ViewActions.click())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        delete
        Espresso.onView(ViewMatchers.withId(R.id.add_favorite)).perform(ViewActions.click())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }



}