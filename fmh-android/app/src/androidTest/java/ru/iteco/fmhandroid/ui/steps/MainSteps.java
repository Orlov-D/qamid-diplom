package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;

import static org.hamcrest.CoreMatchers.not;

import android.os.SystemClock;

import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fmhandroid.ui.elements.MainScreen;

public class MainSteps {
    MainScreen MainScreen = new MainScreen();

    public void isMainScreen() {
        MainScreen.allNews.check(matches(isDisplayed()));
        MainScreen.allClaims.check(matches(isDisplayed()));
    }

    public void expandAllNews() {
        MainScreen.expandAllNews.check(matches(isDisplayed()));
        MainScreen.expandAllNews.perform(click());
    }

    public void expandAllClaims() {
        MainScreen.expandClaims.check(matches(isDisplayed()));
        MainScreen.expandClaims.perform(click());
    }

    public void allNewsNotDisplayed() {
        MainScreen.allNews.check(matches(not(isDisplayed())));
    }

    public void allNewsDisplayed() {
        MainScreen.allNews.check(matches(isDisplayed()));
    }

    public void allClaimsNotDisplayed() {
        MainScreen.allClaims.check(matches(not(isDisplayed())));
    }

    public void allClaimsDisplayed() {
        MainScreen.allClaims.check(matches(isDisplayed()));
    }

    public void openAllNews() {
        MainScreen.allNews.check(matches(isDisplayed()));
        MainScreen.allNews.perform(click());
    }

    public void openAllClaims() {
        MainScreen.allClaims.check(matches(isDisplayed()));
        MainScreen.allClaims.perform(click());
    }

    public void expandSingleNews() {
        MainScreen.expandSingleNews.perform(actionOnItemAtPosition(0, click()));
        MainScreen.newsDescription.check(matches(isDisplayed()));
    }

    public void collapseSingleNews() {
        MainScreen.categoryIcon.perform(click());
        MainScreen.newsDescriptionAfterCollapse.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    public void openSingleClaim() {
        MainScreen.firstClaimExecutorName.perform(click());
    }

    public void createClaim() {
        MainScreen.addNewClaimButton.perform(click());
        SystemClock.sleep(1000);
    }
    }
