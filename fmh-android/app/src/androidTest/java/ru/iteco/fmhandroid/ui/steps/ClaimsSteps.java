package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;

import android.os.SystemClock;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.ClaimScreen;
import ru.iteco.fmhandroid.ui.elements.MainScreen;

public class ClaimsSteps {

    MainScreen MainScreen = new MainScreen();
    ClaimScreen ClaimScreen = new ClaimScreen();

    public void isClaimsScreen() {
        Allure.step("Проверка, что это экран претензий");
        MainScreen.addNewClaimButton.check(matches(isDisplayed()));
        MainScreen.allNews.check(doesNotExist());
    }

    public void openFiltering() {
        Allure.step("Открытие фильтра");
        ClaimScreen.buttonFiltering.perform(click());
        ClaimScreen.titleFiltering.check(matches(isDisplayed()));
    }

    public void clickCheckboxInProgress() {
        Allure.step("Отметить чекбокс в процессе");
        ClaimScreen.inProgress.perform(click());
    }

    public void clickCheckboxOpen() {
        Allure.step("Отметить чекбокс открыт");
        ClaimScreen.open.perform(click());
    }

    public void clickCheckboxExecuted() {
        Allure.step("Отметить чекбокс выполнен");
        ClaimScreen.executed.perform(click());
    }

    public void clickCheckboxCancelled() {
        Allure.step("Отметить чекбокс отменен");
        ClaimScreen.cancelled.perform(click());
    }

    public void clickCancel() {
        Allure.step("Кликнуть отмена");
        ClaimScreen.buttonCancel.perform(click());
    }

    public void clickOK() {
        Allure.step("Кликнуть ОК");
        ClaimScreen.buttonOk.perform(click());
    }

    public void checkCheckboxInProgress(boolean checked) {
        Allure.step("Проверить состояние чекбокса в процессе");
        if (checked) {
            ClaimScreen.inProgress.check(matches(isChecked()));
        } else {
            ClaimScreen.inProgress.check(matches(isNotChecked()));
        }
    }

    public void checkCheckboxOpen(boolean checked) {
        Allure.step("Проверить состояние чекбокса открыт");
        if (checked) {
            ClaimScreen.open.check(matches(isChecked()));
        } else {
            ClaimScreen.open.check(matches(isNotChecked()));
        }
    }

    public void checkCheckboxExecuted(boolean checked) {
        Allure.step("Проверить состояние чекбокса выполнен");
        if (checked) {
            ClaimScreen.executed.check(matches(isChecked()));
        } else {
            ClaimScreen.executed.check(matches(isNotChecked()));
        }
    }

    public void checkCheckboxCancelled(boolean checked) {
        Allure.step("Проверить состояние чекбокса отменен");
        if (checked) {
            ClaimScreen.cancelled.check(matches(isChecked()));
        } else {
            ClaimScreen.cancelled.check(matches(isNotChecked()));
        }
    }

    public void createClaim() {
        Allure.step("Кликнуть создать претензию");
        ClaimScreen.addNewClaimButton.perform(click());
        SystemClock.sleep(1500);
    }
}
