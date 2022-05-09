package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fmhandroid.ui.elements.ControlPanelScreen;
import ru.iteco.fmhandroid.ui.elements.NewsScreen;
import ru.iteco.fmhandroid.ui.utils.Utils.TextHelpers;

public class ControlPanelSteps {
    ControlPanelScreen ControlPanelScreen = new ControlPanelScreen();
    NewsScreen NewsScreen = new NewsScreen();

    public String getFirstNewsPublicationDate() {
        return TextHelpers.getText(ControlPanelScreen.firstPublicationDate);
    }

    public String getFirstNewsCreationDate() {
        return TextHelpers.getText(ControlPanelScreen.firstCreationDate);
    }

    public String getFirstNewsPublicationDateAgain() {
        return TextHelpers.getText(ControlPanelScreen.firstPublicationDateAgain);
    }

    public String getFirstNewsCreationDateAgain() {
        return TextHelpers.getText(ControlPanelScreen.firstCreationDateAgain);
    }

    public String getLastNewsPublicationDate() {
        return TextHelpers.getText(ControlPanelScreen.lastPublicationDate);
    }

    public void createNews() {
        ControlPanelScreen.buttonCreateNews.perform(click());
    }

    public void isControlPanel() {
        NewsScreen.controlPanel.check(matches(isDisplayed()));
    }

    public void checkFirstPublicationDate(String text) {
        ControlPanelScreen.firstPublicationDate.check(matches(withText(text)));
    }

    public void checkFirstPublicationDateNotActive(String text) {
        ControlPanelScreen.firstPublicationDateNotActive.check(matches(withText(text)));
    }

    public void checkFirstPublicationDateActive(String text) {
        ControlPanelScreen.firstPublicationDateActive.check(matches(withText(text)));
    }

    public void checkNewsStatus() {
        ControlPanelScreen.newsStatus.check(matches(withText("Not active")));
    }

    public void checkNewsStatusActive() {
        ControlPanelScreen.newsStatusActive.check(matches(withText("Active")));
    }

    public void clickEditNews() {
        ControlPanelScreen.buttonEditNews.perform(click());
    }

    public void clickEditThisNews() {
        ControlPanelScreen.newsEdit.perform(click());
    }

    public void checkNewsStatusNotActive() {
        ControlPanelScreen.buttonEditNewsNotActive.perform(click());
    }

    public void clickDeleteNews() {
        ControlPanelScreen.buttonDeleteNews.perform(click());
    }

    public void clickDeleteThisNews() {
        ControlPanelScreen.newsDelete.perform(click());
    }

    public void clickNewsTitle() {
        ControlPanelScreen.newsTitle.perform(click());
    }

    public void checkNewsDescription(boolean visible) {
        if (visible) {
            ControlPanelScreen.newsDescription.check(matches(isDisplayed()));
        } else {
            ControlPanelScreen.newsDescription.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        }
    }


}
