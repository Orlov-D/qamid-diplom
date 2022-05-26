package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static ru.iteco.fmhandroid.ui.utils.Utils.nestedScrollTo;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.elements.EditClaimScreen;

public class EditClaimSteps {

    EditClaimScreen EditClaimScreen = new EditClaimScreen();

    @Step("Проверка, что это экран редактирования претензии")
    public void isClaimsEditScreen() {
        EditClaimScreen.claimStatus.check(matches(isDisplayed()));
    }

    @Step("Вернуться назад")
    public void backFromClaim() {
        EditClaimScreen.backButton.perform(nestedScrollTo());
        EditClaimScreen.backButton.perform(click());
    }

}
