package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.elements.CommonElements;
import ru.iteco.fmhandroid.ui.elements.MainScreen;

@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationPage {

    AuthorizationScreen AuthorizationScreen = new AuthorizationScreen();
    CommonElements CommonElements = new CommonElements();
    MainScreen MainScreen = new MainScreen();

    @Rule
    public ActivityTestRule<AppActivity> mActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(7000);
        try {
            AuthorizationScreen.authorization.check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            CommonElements.manImage.perform(click());
            CommonElements.exitButton.perform(click());
        }
    }

    @Test
    public void signInVisible() {
        AuthorizationScreen.authorization.check(matches(isDisplayed()));
        AuthorizationScreen.login.check(matches(isEnabled()));
        AuthorizationScreen.password.check(matches(isEnabled()));
        AuthorizationScreen.buttonSignIn.check(matches(isClickable()));
    }

    @Test
    public void signInWrong() {
        AuthorizationScreen.authorization.check(matches(isDisplayed()));
        AuthorizationScreen.login.check(matches(isEnabled()));
        AuthorizationScreen.password.check(matches(isEnabled()));
        AuthorizationScreen.buttonSignIn.check(matches(isClickable()));
        AuthorizationScreen.buttonSignIn.perform(click());
        ViewInteraction emptyToast = onView(withText(R.string.empty_login_or_password)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        ViewInteraction wrongToast = onView(withText(R.string.wrong_login_or_password)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));

        emptyToast.check(matches(withText("Login and password cannot be empty")));

        SystemClock.sleep(1000);
        AuthorizationScreen.login.perform(typeText(" "));
        AuthorizationScreen.password.perform(typeText(" "));
        AuthorizationScreen.buttonSignIn.perform(click());
        emptyToast.check(matches(withText("Login and password cannot be empty")));

        SystemClock.sleep(1000);
        AuthorizationScreen.login.perform(clearText(), typeText("123"));
        AuthorizationScreen.password.perform(clearText(), typeText("123"));
        AuthorizationScreen.buttonSignIn.perform(click());
        wrongToast.check(matches(withText("Wrong login or password")));
    }

    @Test
    public void signInOK() {
        AuthorizationScreen.authorization.check(matches(isDisplayed()));
        AuthorizationScreen.login.check(matches(isEnabled()));
        AuthorizationScreen.password.check(matches(isEnabled()));
        AuthorizationScreen.buttonSignIn.check(matches(isClickable()));

        AuthorizationScreen.login.perform(typeText("login2"));
        AuthorizationScreen.password.perform(typeText("password2"));
        AuthorizationScreen.buttonSignIn.perform(click());
        SystemClock.sleep(2500);
        MainScreen.allNews.check(matches(isDisplayed()));
        MainScreen.allClaims.check(matches(isDisplayed()));

        CommonElements.manImage.perform(click());
        CommonElements.exitButton.perform(click());
    }

}
