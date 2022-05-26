package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.elements.CreateNewsScreen;

public class CreateNewsSteps {

    CreateNewsScreen CreateNewsScreen = new CreateNewsScreen();

    @Step("Проверка, что это экран создания новости")
    public void isCreateNewsScreen() {
        CreateNewsScreen.title.check(matches(withText("Creating")));
        CreateNewsScreen.subTitle.check(matches(withText("News")));
    }

    @Step("Проверка, что это экран редактирования новости")
    public void isEditNewsScreen() {
        CreateNewsScreen.title.check(matches(withText("Editing")));
        CreateNewsScreen.subTitle.check(matches(withText("News")));
    }
    @Step("Выбрать категорию новости")
    public void selectNewsCategory() {
        CreateNewsScreen.categoryList.perform(click());
        CreateNewsScreen.newsTitle.perform(click());
    }
    @Step("Ввести заголовок")
    public void enterNewsTitle(String text) {
        CreateNewsScreen.newsTitle.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Ввести дату публикации")
    public void enterNewsPublicationDate(String text) {
        CreateNewsScreen.newsDate.perform(replaceText(text));
    }

    @Step("Ввести время")
    public void enterNewsTime(String text) {
        CreateNewsScreen.newsTime.perform(replaceText(text));
    }

    @Step("Ввести описание")
    public void enterNewsDescription(String text) {
        CreateNewsScreen.newsDescription.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Проверить заголовок")
    public void checkNewsTitle(String text) {
        CreateNewsScreen.newsTitle.check(matches(withText(text)));
    }

    @Step("Проверить переключатель")
    public void checkNewsSwitcher() {
        CreateNewsScreen.newsSwitcher.check(matches(allOf(withText("Active"), isDisplayed())));
    }

    @Step("Щелкнуть переключатель")
    public void clickNewsSwitcher() {
        CreateNewsScreen.newsSwitcher.perform(click());
    }

}
