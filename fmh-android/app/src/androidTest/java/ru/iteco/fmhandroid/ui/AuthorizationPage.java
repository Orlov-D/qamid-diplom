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
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.os.SystemClock;
import android.view.View;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;

@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationPage {
    @Rule
    public ActivityTestRule<AppActivity> mActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(7000);
        ViewInteraction textView = onView(
                allOf(withText("Authorization"),
                        withParent(withParent(withId(R.id.nav_host_fragment)))));
        try {
            textView.check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            ViewInteraction man = onView((withId(R.id.authorization_image_button)));
            man.perform(click());
            ViewInteraction exitButton = onView((withText("Log out")));
            exitButton.perform(click());
        }
    }

    @Test
    public void signInVisible() {
        ViewInteraction textView = onView(
                allOf(withText("Authorization"),
                        withParent(withParent(withId(R.id.nav_host_fragment)))));
        textView.check(matches(isDisplayed()));
        ViewInteraction editText = onView(
                allOf(withHint("Login")));
        editText.check(matches(isEnabled()));

        ViewInteraction editText2 = onView(
                allOf(withHint("Password"),
                        withParent(withParent(withId(R.id.password_text_input_layout)))));
        editText2.check(matches(isEnabled()));

        ViewInteraction button = onView(
                allOf(withId(R.id.enter_button), withText("SIGN IN"), withContentDescription("Save"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button.check(matches(isClickable()));
    }

    @Test
    public void signInWrong() {
        ViewInteraction textView = onView(
                allOf(withText("Authorization"),
                        withParent(withParent(withId(R.id.nav_host_fragment)))));
        textView.check(matches(isDisplayed()));
        ViewInteraction login = onView(
                allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))));
        login.check(matches(isEnabled()));
        ViewInteraction password = onView(
                allOf(withHint("Password"),
                        withParent(withParent(withId(R.id.password_text_input_layout)))));
        password.check(matches(isEnabled()));
        ViewInteraction button = onView(
                allOf(withId(R.id.enter_button), withText("SIGN IN"), withContentDescription("Save"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button.check(matches(isClickable()));
        button.perform(click());
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Login and password cannot be empty")));

        SystemClock.sleep(1000);
        login.perform(typeText(" "));
        password.perform(typeText(" "));
        button.perform(click());
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Login and password cannot be empty")));

        SystemClock.sleep(1000);
        login.perform(clearText(), typeText("123"));
        password.perform(clearText(), typeText("123"));
        button.perform(click());
        onView(withText(R.string.wrong_login_or_password))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Wrong login or password")));
    }

    @Test
    public void signInOK() {
        ViewInteraction textView = onView(
                allOf(withText("Authorization"),
                        withParent(withParent(withId(R.id.nav_host_fragment)))));
        textView.check(matches(isDisplayed()));
        ViewInteraction login = onView(
                allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))));
        login.check(matches(isEnabled()));
        ViewInteraction password = onView(
                allOf(withHint("Password"),
                        withParent(withParent(withId(R.id.password_text_input_layout)))));
        password.check(matches(isEnabled()));
        ViewInteraction button = onView(
                allOf(withId(R.id.enter_button), withText("SIGN IN"), withContentDescription("Save"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button.check(matches(isClickable()));

        login.perform(typeText("login2"));
        password.perform(typeText("password2"));
        button.perform(click());
        SystemClock.sleep(2500);
        ViewInteraction news = onView((withText("News")));
        news.check(matches(isDisplayed()));
        ViewInteraction claims = onView((withText("Claims")));
        claims.check(matches(isDisplayed()));

        ViewInteraction man = onView((withId(R.id.authorization_image_button)));
        man.perform(click());
        ViewInteraction exitButton = onView((withText("Log out")));
        exitButton.perform(click());
        SystemClock.sleep(2000);
    }

}
