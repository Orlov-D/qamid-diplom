package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.ui.elements.NewsScreen;
import ru.iteco.fmhandroid.ui.utils.Utils;

public class NewsSteps {
    NewsScreen NewsScreen = new NewsScreen();

    public void isNewsScreen() {
        NewsScreen.news.check(matches(isDisplayed()));
        NewsScreen.buttonSort.check(matches(isDisplayed()));
    }

    public String getFirstNewsTitle() {
        return Utils.TextHelpers.getText(NewsScreen.firstNews);
    }

    public String getLastNewsTitle() {
        return Utils.TextHelpers.getText(NewsScreen.lastNews);
    }

    public String getFirstNewsAgainTitle() {
        return Utils.TextHelpers.getText(NewsScreen.firstNewsAgain);
    }

    public void clickSortButton() {
        NewsScreen.buttonSort.perform(click());
    }

    public void goToControlPanel() {
        NewsScreen.buttonControlPanel.perform(click());
        NewsScreen.controlPanel.check(matches(isDisplayed()));
    }

    public void openFilter() {
        NewsScreen.buttonFilter.perform(click());
    }

    public void checkFirstNewsDate(String text) {
        NewsScreen.firstNewsDate.check(matches(withText(text)));
    }

}
