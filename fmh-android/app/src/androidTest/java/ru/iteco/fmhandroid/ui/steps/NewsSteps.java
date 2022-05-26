package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.elements.NewsScreen;
import ru.iteco.fmhandroid.ui.utils.Utils;

public class NewsSteps {
    NewsScreen NewsScreen = new NewsScreen();

    @Step("Проверить, что это экран новостей")
    public void isNewsScreen() {
        NewsScreen.news.check(matches(isDisplayed()));
        NewsScreen.buttonSort.check(matches(isDisplayed()));
    }

    @Step("Получить название первой новости")
    public String getFirstNewsTitle() {
        return Utils.TextHelpers.getText(NewsScreen.firstNews);
    }

    @Step("Получить название последней новости")
    public String getLastNewsTitle() {
        return Utils.TextHelpers.getText(NewsScreen.lastNews);
    }

    @Step("Получить название новой первой новости")
    public String getFirstNewsAgainTitle() {
        return Utils.TextHelpers.getText(NewsScreen.firstNewsAgain);
    }

    @Step("Сортировать")
    public void clickSortButton() {
        NewsScreen.buttonSort.perform(click());
    }

    @Step("Перейти в панель управления")
    public void goToControlPanel() {
        NewsScreen.buttonControlPanel.perform(click());
        NewsScreen.controlPanel.check(matches(isDisplayed()));
    }

    @Step("Открыть фильтр")
    public void openFilter() {
        NewsScreen.buttonFilter.perform(click());
    }

    @Step("Проверить дату первой новости")
    public void checkFirstNewsDate(String text) {
        NewsScreen.firstNewsDate.check(matches(withText(text)));
    }
}
