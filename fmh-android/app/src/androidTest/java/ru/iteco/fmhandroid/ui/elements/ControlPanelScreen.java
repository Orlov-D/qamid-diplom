package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.AppActivityTest.newNewsTitle;
import static ru.iteco.fmhandroid.ui.AppActivityTest.newsDescriptionString;
import static ru.iteco.fmhandroid.ui.AppActivityTest.newsTitleString;
import static ru.iteco.fmhandroid.ui.utils.Utils.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ControlPanelScreen {
    public ViewInteraction firstPublicationDate = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0));
    public ViewInteraction firstCreationDate = onView(withIndex(withId(R.id.news_item_create_date_text_view), 0));
    public ViewInteraction firstPublicationDateAgain = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0));
    public ViewInteraction firstCreationDateAgain = onView(withIndex(withId(R.id.news_item_create_date_text_view), 0));
    public ViewInteraction lastPublicationDate = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0));
    public ViewInteraction buttonCreateNews = onView(withId(R.id.add_news_image_view));
    public ViewInteraction buttonEditNews = onView(withIndex(withId(R.id.edit_news_item_image_view), 0));
    public ViewInteraction newsStatus = onView(withIndex(withId(R.id.news_item_published_text_view), 0));
    public ViewInteraction newsStatusActive = onView(withIndex(withId(R.id.news_item_published_text_view), 0));
    public ViewInteraction buttonDeleteNews = onView(withId(R.id.delete_news_item_image_view));
    public ViewInteraction firstPublicationDateNotActive = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0));
    public ViewInteraction firstPublicationDateActive = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0));
    public ViewInteraction buttonEditNewsNotActive = onView(withIndex(withId(R.id.edit_news_item_image_view), 0));
    public ViewInteraction newsDescription = onView(withText(newsDescriptionString));
    public ViewInteraction newsEdit = onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(newsTitleString))))))));
    public ViewInteraction newsDelete = onView(allOf(withId(R.id.delete_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(newNewsTitle))))))));
    public ViewInteraction newsTitle = onView(withText(newsTitleString));

}
