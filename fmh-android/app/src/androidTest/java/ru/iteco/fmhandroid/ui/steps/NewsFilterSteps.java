package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;

import ru.iteco.fmhandroid.ui.elements.NewsFilterScreen;

public class NewsFilterSteps {
    NewsFilterScreen NewsFilterScreen = new NewsFilterScreen();

    public void enterPublishDateStart(String text) {
        NewsFilterScreen.publishDateStart.perform(replaceText(text));
    }

    public void enterPublishDateEnd(String text) {
        NewsFilterScreen.publishDateEnd.perform(replaceText(text));
    }

    public void clickFilter() {
        NewsFilterScreen.buttonFilter.perform(click());
    }

    public void clickCheckboxActive() {
        NewsFilterScreen.checkboxActive.perform(click());
    }

    public void clickCheckboxNotActive() {
        NewsFilterScreen.checkboxNotActive.perform(click());
    }

    public void checkCheckboxActive(boolean checked) {
        if (checked) {
            NewsFilterScreen.checkboxActive.check(matches(isChecked()));
        } else {
            NewsFilterScreen.checkboxActive.check(matches(isNotChecked()));
        }
    }

    public void checkCheckboxNotActive(boolean checked) {
        if (checked) {
            NewsFilterScreen.checkboxNotActive.check(matches(isChecked()));
        } else {
            NewsFilterScreen.checkboxNotActive.check(matches(isNotChecked()));
        }


    }
}
