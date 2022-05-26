package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.elements.ControlPanelScreen;
import ru.iteco.fmhandroid.ui.elements.NewsScreen;
import ru.iteco.fmhandroid.ui.utils.Utils.TextHelpers;

public class ControlPanelSteps {
    ControlPanelScreen ControlPanelScreen = new ControlPanelScreen();
    NewsScreen NewsScreen = new NewsScreen();

    @Step("Получить дату публикации первой новости")
    public String getFirstNewsPublicationDate() {
        return TextHelpers.getText(ControlPanelScreen.firstPublicationDate);
    }

    @Step("Получить дату создания первой новости")
    public String getFirstNewsCreationDate() {
        return TextHelpers.getText(ControlPanelScreen.firstCreationDate);
    }

    @Step("Получить дату публикации новой первой новости")
    public String getFirstNewsPublicationDateAgain() {
        return TextHelpers.getText(ControlPanelScreen.firstPublicationDateAgain);
    }

    @Step("Получить дату создания новой первой новости")
    public String getFirstNewsCreationDateAgain() {
        return TextHelpers.getText(ControlPanelScreen.firstCreationDateAgain);
    }

    @Step("Получить дату публикации последней новости")
    public String getLastNewsPublicationDate() {
        return TextHelpers.getText(ControlPanelScreen.lastPublicationDate);
    }

    @Step("Перейти к экрану создания новости")
    public void createNews() {
        ControlPanelScreen.buttonCreateNews.perform(click());
    }

    @Step("Проверка, что это экран управления")
    public void isControlPanel() {
        NewsScreen.controlPanel.check(matches(isDisplayed()));
    }

    @Step("Проверить дату публикации первой новости")
    public void checkFirstPublicationDate(String text) {
        ControlPanelScreen.firstPublicationDate.check(matches(withText(text)));
    }

    @Step("Проверить дату публикации первой не активной новости")
    public void checkFirstPublicationDateNotActive(String text) {
        ControlPanelScreen.firstPublicationDateNotActive.check(matches(withText(text)));
    }

    @Step("Проверить дату публикации первой активной новости")
    public void checkFirstPublicationDateActive(String text) {
        ControlPanelScreen.firstPublicationDateActive.check(matches(withText(text)));
    }

    @Step("Проверить статус новости")
    public void checkNewsStatus() {
        ControlPanelScreen.newsStatus.check(matches(withText("Not active")));
    }

    @Step("Проверить статус активной новости")
    public void checkNewsStatusActive() {
        ControlPanelScreen.newsStatusActive.check(matches(withText("Active")));
    }

    @Step("Открыть окно изменения новости")
    public void clickEditNews() {
        ControlPanelScreen.buttonEditNews.perform(click());
    }

    @Step("Открыть окно изменения этой новости")
    public void clickEditThisNews() {
        ControlPanelScreen.newsEdit.perform(click());
    }

    @Step("Проверить статус не активной новости")
    public void checkNewsStatusNotActive() {
        ControlPanelScreen.buttonEditNewsNotActive.perform(click());
    }

    @Step("Удалить новость")
    public void clickDeleteNews() {
        ControlPanelScreen.buttonDeleteNews.perform(click());
    }

    @Step("Удалить эту новость")
    public void clickDeleteThisNews() {
        ControlPanelScreen.newsDelete.perform(click());
    }

    @Step("Нажать на заголовок")
    public void clickNewsTitle() {
        ControlPanelScreen.newsTitle.perform(click());
    }

    @Step("Проверить описание новости")
    public void checkNewsDescription(boolean visible) {
        if (visible) {
            ControlPanelScreen.newsDescription.check(matches(isDisplayed()));
        } else {
            ControlPanelScreen.newsDescription.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        }
    }
}
