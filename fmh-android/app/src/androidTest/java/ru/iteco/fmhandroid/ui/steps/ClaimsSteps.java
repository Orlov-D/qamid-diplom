package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;

import ru.iteco.fmhandroid.ui.elements.ClaimScreen;
import ru.iteco.fmhandroid.ui.elements.MainScreen;

public class ClaimsSteps {

    MainScreen MainScreen = new MainScreen();
    ClaimScreen ClaimScreen = new ClaimScreen();

    public void isClaimsScreen() {
        MainScreen.addNewClaimButton.check(matches(isDisplayed()));
        MainScreen.allNews.check(doesNotExist());
    }

    public void openFiltering() {
        ClaimScreen.buttonFiltering.perform(click());
        ClaimScreen.titleFiltering.check(matches(isDisplayed()));
    }

    public void clickCheckboxInProgress() {
        ClaimScreen.inProgress.perform(click());
    }

    public void clickCheckboxOpen() {
        ClaimScreen.open.perform(click());
    }

    public void clickCheckboxExecuted() {
        ClaimScreen.executed.perform(click());
    }

    public void clickCheckboxCancelled() {
        ClaimScreen.cancelled.perform(click());
    }

    public void clickCancel() {
        ClaimScreen.buttonCancel.perform(click());
    }

    public void clickOK() {
        ClaimScreen.buttonOk.perform(click());
    }

    public void checkCheckboxInProgress(boolean checked) {
        if (checked) {
            ClaimScreen.inProgress.check(matches(isChecked()));
        } else {
            ClaimScreen.inProgress.check(matches(isNotChecked()));
        }
    }

    public void checkCheckboxOpen(boolean checked) {
        if (checked) {
            ClaimScreen.open.check(matches(isChecked()));
        } else {
            ClaimScreen.open.check(matches(isNotChecked()));
        }
    }

    public void checkCheckboxExecuted(boolean checked) {
        if (checked) {
            ClaimScreen.executed.check(matches(isChecked()));
        } else {
            ClaimScreen.executed.check(matches(isNotChecked()));
        }
    }

    public void checkCheckboxCancelled(boolean checked) {
        if (checked) {
            ClaimScreen.cancelled.check(matches(isChecked()));
        } else {
            ClaimScreen.cancelled.check(matches(isNotChecked()));
        }
    }

    public void createClaim() {
        ClaimScreen.addNewClaimButton.perform(click());
    }
}
