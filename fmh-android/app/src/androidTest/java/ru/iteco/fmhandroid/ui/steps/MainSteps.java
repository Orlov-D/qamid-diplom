package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static org.hamcrest.CoreMatchers.not;

import android.os.SystemClock;

import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.elements.MainScreen;

public class MainSteps {
    MainScreen MainScreen = new MainScreen();

    @Step("Проверка, что это главный экран")
    public void isMainScreen() {
        MainScreen.allNews.check(matches(isDisplayed()));
        MainScreen.allClaims.check(matches(isDisplayed()));
    }

    @Step("Развернуть блок новостей")
    public void expandAllNews() {
        MainScreen.expandAllNews.check(matches(isDisplayed()));
        MainScreen.expandAllNews.perform(click());
    }

    @Step("Развернуть блок претензий")
    public void expandAllClaims() {
        MainScreen.expandClaims.check(matches(isDisplayed()));
        MainScreen.expandClaims.perform(click());
    }

    @Step("Не видно ссылки Все новости")
    public void allNewsNotDisplayed() {
        MainScreen.allNews.check(matches(not(isDisplayed())));
    }

    @Step("Видна ссылка Все новости")
    public void allNewsDisplayed() {
        MainScreen.allNews.check(matches(isDisplayed()));
    }

    @Step("Не видно ссылки Все претензии")
    public void allClaimsNotDisplayed() {
        MainScreen.allClaims.check(matches(not(isDisplayed())));
    }

    @Step("Видна ссылка Все претензии")
    public void allClaimsDisplayed() {
        MainScreen.allClaims.check(matches(isDisplayed()));
    }

    @Step("Кликнуть Все новости")
    public void openAllNews() {
        MainScreen.allNews.check(matches(isDisplayed()));
        MainScreen.allNews.perform(click());
    }

    @Step("Кликнуть Все претензии")
    public void openAllClaims() {
        MainScreen.allClaims.check(matches(isDisplayed()));
        MainScreen.allClaims.perform(click());
    }

    @Step("Развернуть новость")
    public void expandSingleNews() {
        MainScreen.expandSingleNews.perform(actionOnItemAtPosition(0, click()));
        MainScreen.newsDescription.check(matches(isDisplayed()));
    }

    @Step("Свернуть новость")
    public void collapseSingleNews() {
        MainScreen.categoryIcon.perform(click());
        MainScreen.newsDescriptionAfterCollapse.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Step("Открыть претензию")
    public void openSingleClaim() {
        MainScreen.firstClaimExecutorName.perform(click());
        SystemClock.sleep(2000);
    }

    @Step("Создать претензию")
    public void createClaim() {
        MainScreen.addNewClaimButton.perform(click());
        SystemClock.sleep(1000);
    }
}
