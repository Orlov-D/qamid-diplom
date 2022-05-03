package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class CreateClaimScreen {
    public ViewInteraction title = onView(withId(R.id.custom_app_bar_title_text_view));
    public ViewInteraction subTitle = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    public ViewInteraction claimTitle = onView(withId(R.id.title_edit_text));
    public ViewInteraction toastEmptyFields = onView(withText("Fill empty fields"));
    public ViewInteraction executorList = onView(withId(R.id.executor_drop_menu_auto_complete_text_view));
    public ViewInteraction claimDate = onView(withId(R.id.date_in_plan_text_input_edit_text));
    public ViewInteraction claimTime = onView(withId(R.id.time_in_plan_text_input_edit_text));
    public ViewInteraction claimDescription = onView(withId(R.id.description_edit_text));

}
