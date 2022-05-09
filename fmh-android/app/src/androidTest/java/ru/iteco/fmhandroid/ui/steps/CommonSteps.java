package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;

import ru.iteco.fmhandroid.ui.elements.CommonElements;

public class CommonSteps {

    CommonElements CommonElements = new CommonElements();

    public void logout() {
        CommonElements.manImage.perform(click());
        CommonElements.exitButton.perform(click());
    }

    public void clickSave() {
        CommonElements.buttonSave.perform(click());
    }

    public void clickOK() {
        CommonElements.buttonOkText.perform(click());
    }

    public void clickCancel() {
        CommonElements.buttonCancel.perform(click());
    }

    public void clickCancelText() {
        CommonElements.buttonCancelText.perform(click());
    }

    public void goToThematicQuotes() {
        CommonElements.thematicQuotes.perform(click());
    }

    public void goToScreen(String screen) {
        CommonElements.mainMenu.perform(click());
        switch (screen) {
            case ("Main"):
                CommonElements.menuMain.perform(click());
            case ("Claims"):
                CommonElements.menuClaims.perform(click());
            case ("News"):
                CommonElements.menuNews.perform(click());
            case ("About"):
                CommonElements.menuAbout.perform(click());
        }
    }
}
