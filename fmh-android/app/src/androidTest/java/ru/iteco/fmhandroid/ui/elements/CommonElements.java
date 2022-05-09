package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class CommonElements {
    public ViewInteraction buttonSave = onView(withId(R.id.save_button));
    public ViewInteraction buttonOkText = onView(withText("OK"));
    public ViewInteraction buttonCancel = onView(withId(R.id.cancel_button));
    public ViewInteraction buttonCancelText = onView(withText("CANCEL"));
    public ViewInteraction mainMenu = onView(withId(R.id.main_menu_image_button));
    public ViewInteraction menuMain = onView(withText("Main"));
    public ViewInteraction menuClaims = onView(withText("Claims"));
    public ViewInteraction menuNews = onView(withText("News"));
    public ViewInteraction menuAbout = onView(withText("About"));
    public ViewInteraction thematicQuotes = onView(withId(R.id.our_mission_image_button));
    public ViewInteraction manImage = onView((withId(R.id.authorization_image_button)));
    public ViewInteraction exitButton = onView((withText("Log out")));

}
