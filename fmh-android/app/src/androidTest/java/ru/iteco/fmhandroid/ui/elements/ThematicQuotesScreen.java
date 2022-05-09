package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.utils.Utils.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ThematicQuotesScreen {
    public ViewInteraction title = onView(withId(R.id.our_mission_title_text_view));
    public ViewInteraction icon = onView(withIndex(withId(R.id.our_mission_item_image_view), 0));
    public ViewInteraction thematicTitle = onView(withIndex(withId(R.id.our_mission_item_title_text_view), 0));
    public ViewInteraction thematicTitleClickable = onView(withIndex(withId(R.id.our_mission_item_title_text_view), 0));
    public ViewInteraction thematicTitleClickable2 = onView(withIndex(withId(R.id.our_mission_item_title_text_view), 0));
    public ViewInteraction thematicDescription = onView(withIndex(withId(R.id.our_mission_item_description_text_view), 0));
    public ViewInteraction thematicDescriptionAfterClick = onView(withIndex(withId(R.id.our_mission_item_description_text_view), 0));

}
