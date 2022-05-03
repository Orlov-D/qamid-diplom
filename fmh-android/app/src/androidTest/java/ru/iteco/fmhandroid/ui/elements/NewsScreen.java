package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.utils.Utils.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsScreen {
    public ViewInteraction news = onView(withText("News"));
    public ViewInteraction firstNews = onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    public ViewInteraction lastNews = onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    public ViewInteraction firstNewsAgain = onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    public ViewInteraction buttonControlPanel = onView(withId(R.id.edit_news_material_button));
    public ViewInteraction controlPanel = onView(withText("Control panel"));
    public ViewInteraction buttonSort = onView(withId(R.id.sort_news_material_button));
    public ViewInteraction buttonFilter = onView(withId(R.id.filter_news_material_button));
    public ViewInteraction firstNewsDate = onView(withIndex(withId(R.id.news_item_date_text_view), 0));

}
