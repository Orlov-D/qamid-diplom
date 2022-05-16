package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static ru.iteco.fmhandroid.ui.utils.Utils.nestedScrollTo;

import ru.iteco.fmhandroid.ui.elements.EditClaimScreen;

public class EditClaimSteps {

    EditClaimScreen EditClaimScreen = new EditClaimScreen();

    public void isClaimsEditScreen() {
        EditClaimScreen.claimStatus.check(matches(isDisplayed()));
    }

    public void backFromClaim() {
        EditClaimScreen.backButton.perform(nestedScrollTo());
        EditClaimScreen.backButton.perform(click());
    }

}
