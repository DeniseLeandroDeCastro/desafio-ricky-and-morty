package br.com.denisecastro.desafiorickandmorty

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Test


class ActivityListTest {

    @Test
    fun toShowTitleCharacter() {
        launch(MainActivity::class.java)
        onView(withText("Personagens")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowFieldToSearchByCharacterName() {
        launch(MainActivity::class.java)
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
    }
}