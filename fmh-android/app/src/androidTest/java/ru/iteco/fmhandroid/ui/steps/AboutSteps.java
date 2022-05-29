package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.AboutScreen;

public class AboutSteps {
    AboutScreen AboutScreen = new AboutScreen();

    public void checkEverythingYouWant() {
        Allure.step("Проверка всех данных");
        AboutScreen.versionTitle.check(matches(allOf(withText("Version:"), isDisplayed())));
        AboutScreen.version.check(matches(allOf(withText("1.0.0"), isDisplayed())));
        AboutScreen.privacyPolicy.check(matches(allOf(withText("https://vhospice.org/#/privacy-policy/"), isDisplayed(), isClickable())));
        AboutScreen.termsTitle.check(matches(allOf(withText("Terms of use:"), isDisplayed())));
        AboutScreen.termsUrl.check(matches(allOf(withText("https://vhospice.org/#/terms-of-use"), isDisplayed(), isClickable())));
        AboutScreen.copyright.check(matches(allOf(withText("© I-Teco, 2022"), isDisplayed())));
    }

    public void goBack() {
        Allure.step("Возврат к предыдущему экрану");
        AboutScreen.buttonBack.perform(click());
    }
}
