package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.elements.AuthorizationScreen;

public class AuthorizationSteps {
    AuthorizationScreen AuthorizationScreen = new AuthorizationScreen();

    @Step("Проверка, что это экран авторизации")
    public void isAuthorizationScreen() {
        AuthorizationScreen.authorization.check(matches(isDisplayed()));
    }

    @Step("Ввод логина")
    public void enterLogin(String text) {
        AuthorizationScreen.login.check(matches(isEnabled()));
        AuthorizationScreen.login.perform(replaceText(text));
    }

    @Step("Ввод пароля")
    public void enterPassword(String text) {
        AuthorizationScreen.password.check(matches(isEnabled()));
        AuthorizationScreen.password.perform(replaceText(text));
    }

    @Step("Нажатие кнопки входа")
    public void signIn() {
        AuthorizationScreen.buttonSignIn.check(matches(isClickable()));
        AuthorizationScreen.buttonSignIn.perform(click());
    }

}
