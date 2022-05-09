package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import ru.iteco.fmhandroid.ui.elements.CreateNewsScreen;

public class CreateNewsSteps {

    CreateNewsScreen CreateNewsScreen = new CreateNewsScreen();

    public void isCreateNewsScreen() {
        CreateNewsScreen.title.check(matches(withText("Creating")));
        CreateNewsScreen.subTitle.check(matches(withText("News")));
    }

    public void isEditNewsScreen() {
        CreateNewsScreen.title.check(matches(withText("Editing")));
        CreateNewsScreen.subTitle.check(matches(withText("News")));
    }

    public void selectNewsCategory() {
        CreateNewsScreen.categoryList.perform(click());
        CreateNewsScreen.newsTitle.perform(click());
    }

    public void enterNewsTitle(String text) {
        CreateNewsScreen.newsTitle.perform(replaceText(text), closeSoftKeyboard());
    }

    public void enterNewsPublicationDate(String text) {
        CreateNewsScreen.newsDate.perform(replaceText(text));
    }

    public void enterNewsTime(String text) {
        CreateNewsScreen.newsTime.perform(replaceText(text));
    }

    public void enterNewsDescription(String text) {
        CreateNewsScreen.newsDescription.perform(replaceText(text), closeSoftKeyboard());
    }

    public void checkNewsTitle(String text) {
        CreateNewsScreen.newsTitle.check(matches(withText(text)));
    }

    public void checkNewsSwitcher() {
        CreateNewsScreen.newsSwitcher.check(matches(allOf(withText("Active"), isDisplayed())));
    }

    public void clickNewsSwitcher() {
        CreateNewsScreen.newsSwitcher.perform(click());
    }

}
