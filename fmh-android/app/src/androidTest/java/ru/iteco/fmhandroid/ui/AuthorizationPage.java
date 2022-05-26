package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
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
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationPage {

    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    CommonSteps CommonSteps = new CommonSteps();
    MainSteps MainSteps = new MainSteps();

    @Rule
    public ActivityTestRule<AppActivity> mActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(7000);
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            CommonSteps.logout();
        }
    }

    @Test
    @DisplayName("Проверка входа с пустой формой и под несуществующим пользователем")
    public void signInWrong() {
        AuthorizationSteps.isAuthorizationScreen();
        AuthorizationSteps.signIn();
        ViewInteraction emptyToast = onView(withText(R.string.empty_login_or_password)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        ViewInteraction wrongToast = onView(withText(R.string.wrong_login_or_password)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));

        emptyToast.check(matches(withText("Login and password cannot be empty")));

        SystemClock.sleep(1500);
        AuthorizationSteps.enterLogin(" ");
        AuthorizationSteps.enterPassword(" ");
        AuthorizationSteps.signIn();
        emptyToast.check(matches(withText("Login and password cannot be empty")));

        SystemClock.sleep(1500);
        AuthorizationSteps.enterLogin("123");
        AuthorizationSteps.enterPassword("123");
        AuthorizationSteps.signIn();
        SystemClock.sleep(1500);
        wrongToast.check(matches(withText("Wrong login or password")));
    }

    @Test
    @DisplayName("Успешный вход за пользователя и выход из приложения")
    public void signInOK() {
        AuthorizationSteps.isAuthorizationScreen();
        AuthorizationSteps.enterLogin("login2");
        AuthorizationSteps.enterPassword("password2");
        AuthorizationSteps.signIn();
        SystemClock.sleep(2500);
        MainSteps.isMainScreen();

        CommonSteps.logout();
    }

}
