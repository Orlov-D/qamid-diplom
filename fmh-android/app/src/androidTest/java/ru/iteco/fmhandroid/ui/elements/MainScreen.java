package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.utils.Utils.childAtPosition;
import static ru.iteco.fmhandroid.ui.utils.Utils.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainScreen {
    public ViewInteraction allClaims = onView((withId(R.id.all_claims_text_view)));
    public ViewInteraction addNewClaimButton = onView((withId(R.id.add_new_claim_material_button)));
    public ViewInteraction allNews = onView((withId(R.id.all_news_text_view)));
    public ViewInteraction expandAllNews = onView(allOf(withId(R.id.expand_material_button), childAtPosition(childAtPosition(withId(R.id.container_list_news_include_on_fragment_main), 0), 4)));
    public ViewInteraction expandSingleNews = onView(allOf(withId(R.id.news_list_recycler_view), childAtPosition(withId(R.id.all_news_cards_block_constraint_layout), 0)));
    public ViewInteraction expandClaims = onView(allOf(withId(R.id.expand_material_button), childAtPosition(childAtPosition(withId(R.id.container_list_claim_include_on_fragment_main), 0), 3)));
    public ViewInteraction newsDescription = onView(withIndex(withId(R.id.view_news_item_image_view), 0));
    public ViewInteraction categoryIcon = onView(withIndex(withId(R.id.category_icon_image_view), 0));
    public ViewInteraction newsDescriptionAfterCollapse = onView(withIndex(withId(R.id.view_news_item_image_view), 0));
    public ViewInteraction firstClaimExecutorName = onView(allOf(withIndex(withId(R.id.executor_name_material_text_view), 0)));
    public ViewInteraction news = onView((withText("News")));

}
