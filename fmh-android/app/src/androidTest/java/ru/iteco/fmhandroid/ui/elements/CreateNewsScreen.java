package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class CreateNewsScreen {
    public ViewInteraction title = onView(withId(R.id.custom_app_bar_title_text_view));
    public ViewInteraction subTitle = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    public ViewInteraction categoryList = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction newsTitle = onView(withId(R.id.news_item_title_text_input_edit_text));
    public ViewInteraction newsDate = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public ViewInteraction newsTime = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public ViewInteraction newsDescription = onView(withId(R.id.news_item_description_text_input_edit_text));
    public ViewInteraction newsSwitcher = onView(withId(R.id.switcher));

}
