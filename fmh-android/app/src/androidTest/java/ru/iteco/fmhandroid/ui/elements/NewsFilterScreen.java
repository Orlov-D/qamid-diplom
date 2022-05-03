package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsFilterScreen {
    public ViewInteraction publishDateStart = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    public ViewInteraction publishDateEnd = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    public ViewInteraction buttonFilter = onView(withId(R.id.filter_button));
    public ViewInteraction checkboxActive = onView(withId(R.id.filter_news_active_material_check_box));
    public ViewInteraction checkboxNotActive = onView(withId(R.id.filter_news_inactive_material_check_box));

}
