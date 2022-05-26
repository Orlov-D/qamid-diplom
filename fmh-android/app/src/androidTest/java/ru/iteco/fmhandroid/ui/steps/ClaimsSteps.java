package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;

import android.os.SystemClock;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.elements.ClaimScreen;
import ru.iteco.fmhandroid.ui.elements.MainScreen;

public class ClaimsSteps {

    MainScreen MainScreen = new MainScreen();
    ClaimScreen ClaimScreen = new ClaimScreen();

    @Step("Проверка, что это экран претензий")
    public void isClaimsScreen() {
        MainScreen.addNewClaimButton.check(matches(isDisplayed()));
        MainScreen.allNews.check(doesNotExist());
    }

    @Step("Открытие фильтра")
    public void openFiltering() {
        ClaimScreen.buttonFiltering.perform(click());
        ClaimScreen.titleFiltering.check(matches(isDisplayed()));
    }

    @Step("Отметить чекбокс в процессе")
    public void clickCheckboxInProgress() {
        ClaimScreen.inProgress.perform(click());
    }

    @Step("Отметить чекбокс открыт")
    public void clickCheckboxOpen() {
        ClaimScreen.open.perform(click());
    }

    @Step("Отметить чекбокс выполнен")
    public void clickCheckboxExecuted() {
        ClaimScreen.executed.perform(click());
    }

    @Step("Отметить чекбокс отменен")
    public void clickCheckboxCancelled() {
        ClaimScreen.cancelled.perform(click());
    }

    @Step("Кликнуть отмена")
    public void clickCancel() {
        ClaimScreen.buttonCancel.perform(click());
    }

    @Step("Кликнуть ОК")
    public void clickOK() {
        ClaimScreen.buttonOk.perform(click());
    }

    @Step("Проверить состояние чекбокса в процессе")
    public void checkCheckboxInProgress(boolean checked) {
        if (checked) {
            ClaimScreen.inProgress.check(matches(isChecked()));
        } else {
            ClaimScreen.inProgress.check(matches(isNotChecked()));
        }
    }

    @Step("Проверить состояние чекбокса открыт")
    public void checkCheckboxOpen(boolean checked) {
        if (checked) {
            ClaimScreen.open.check(matches(isChecked()));
        } else {
            ClaimScreen.open.check(matches(isNotChecked()));
        }
    }

    @Step("Проверить состояние чекбокса выполнен")
    public void checkCheckboxExecuted(boolean checked) {
        if (checked) {
            ClaimScreen.executed.check(matches(isChecked()));
        } else {
            ClaimScreen.executed.check(matches(isNotChecked()));
        }
    }

    @Step("Проверить состояние чекбокса отменен")
    public void checkCheckboxCancelled(boolean checked) {
        if (checked) {
            ClaimScreen.cancelled.check(matches(isChecked()));
        } else {
            ClaimScreen.cancelled.check(matches(isNotChecked()));
        }
    }

    @Step("Кликнуть создать претензию")
    public void createClaim() {
        ClaimScreen.addNewClaimButton.perform(click());
        SystemClock.sleep(1500);
    }
}
