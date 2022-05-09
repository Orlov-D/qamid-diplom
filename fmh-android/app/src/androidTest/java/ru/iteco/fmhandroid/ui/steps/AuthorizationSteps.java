package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;

import ru.iteco.fmhandroid.ui.elements.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.elements.CommonElements;
import ru.iteco.fmhandroid.ui.elements.MainScreen;

public class AuthorizationSteps {
    AuthorizationScreen AuthorizationScreen = new AuthorizationScreen();
    CommonElements CommonElements = new CommonElements();
    MainScreen MainScreen = new MainScreen();

    public void isAuthorizationScreen() {
        AuthorizationScreen.authorization.check(matches(isDisplayed()));
    }

    public void enterLogin(String text) {
        AuthorizationScreen.login.check(matches(isEnabled()));
        AuthorizationScreen.login.perform(replaceText(text));
    }

    public void enterPassword(String text) {
        AuthorizationScreen.password.check(matches(isEnabled()));
        AuthorizationScreen.password.perform(replaceText(text));
    }

    public void signIn() {
        AuthorizationScreen.buttonSignIn.check(matches(isClickable()));
        AuthorizationScreen.buttonSignIn.perform(click());
    }

}
