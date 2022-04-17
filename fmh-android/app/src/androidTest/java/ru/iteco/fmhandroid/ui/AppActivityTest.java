package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.iteco.fmhandroid.R;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppActivityTest {

    @Rule
    public ActivityTestRule<AppActivity> mActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static ViewAction nestedScrollTo() {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return Matchers.allOf(
                        isDescendantOfA(isAssignableFrom(NestedScrollView.class)),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE));
            }

            @Override
            public String getDescription() {
                return "View is not NestedScrollView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                try {
                    NestedScrollView nestedScrollView = (NestedScrollView)
                            findFirstParentLayoutOfClass(view);
                    if (nestedScrollView != null) {
                        nestedScrollView.scrollTo(0, view.getTop());
                    } else {
                        throw new Exception("Unable to find NestedScrollView parent.");
                    }
                } catch (Exception e) {
                    throw new PerformException.Builder()
                            .withActionDescription(this.getDescription())
                            .withViewDescription(HumanReadables.describe(view))
                            .withCause(e)
                            .build();
                }
                uiController.loopMainThreadUntilIdle();
            }

        };
    }

    public static class TextHelpers {
        public static String getText(ViewInteraction matcher) {
            final String[] text = new String[1];
            ViewAction va = new ViewAction() {

                @Override
                public Matcher<View> getConstraints() {
                    return isAssignableFrom(TextView.class);
                }

                @Override
                public String getDescription() {
                    return "Text of the view";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    TextView tv = (TextView) view;
                    text[0] = tv.getText().toString();
                }
            };

            matcher.perform(va);

            return text[0];
        }
    }

    private static View findFirstParentLayoutOfClass(View view) {
        ViewParent parent = new FrameLayout(view.getContext());
        ViewParent incrementView = null;
        int i = 0;
        while (parent != null && !(parent.getClass() == NestedScrollView.class)) {
            if (i == 0) {
                parent = findParent(view);
            } else {
                parent = findParent(incrementView);
            }
            incrementView = parent;
            i++;
        }
        return (View) parent;
    }

    private static ViewParent findParent(View view) {
        return view.getParent();
    }

    private static ViewParent findParent(ViewParent view) {
        return view.getParent();
    }

    public void login() {
        SystemClock.sleep(8000);
        ViewInteraction textView = onView(
                allOf(withText("Authorization"),
                        withParent(withParent(withId(R.id.nav_host_fragment)))));
        textView.check(matches(isDisplayed()));
        ViewInteraction login = onView(
                allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))));
        login.check(matches(isEnabled()));
        ViewInteraction password = onView(
                allOf(withHint("Password"),
                        withParent(withParent(withId(R.id.password_text_input_layout)))));
        password.check(matches(isEnabled()));
        ViewInteraction button = onView(
                allOf(withId(R.id.enter_button), withText("SIGN IN"), withContentDescription("Save"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button.check(matches(isClickable()));
        login.perform(typeText("login2"));
        password.perform(typeText("password2"));
        button.perform(click());
    }

    @Test
    public void signInVisible() {
        SystemClock.sleep(8000);
        ViewInteraction textView = onView(
                allOf(withText("Authorization"),
                        withParent(withParent(withId(R.id.nav_host_fragment)))));
        textView.check(matches(isDisplayed()));
        ViewInteraction editText = onView(
                allOf(withHint("Login")));
        editText.check(matches(isEnabled()));

        ViewInteraction editText2 = onView(
                allOf(withHint("Password"),
                        withParent(withParent(withId(R.id.password_text_input_layout)))));
        editText2.check(matches(isEnabled()));

        ViewInteraction button = onView(
                allOf(withId(R.id.enter_button), withText("SIGN IN"), withContentDescription("Save"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button.check(matches(isClickable()));
    }

    @Test
    public void signInWrong() {
        SystemClock.sleep(8000);
        ViewInteraction textView = onView(
                allOf(withText("Authorization"),
                        withParent(withParent(withId(R.id.nav_host_fragment)))));
        textView.check(matches(isDisplayed()));
        ViewInteraction login = onView(
                allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))));
        login.check(matches(isEnabled()));
        ViewInteraction password = onView(
                allOf(withHint("Password"),
                        withParent(withParent(withId(R.id.password_text_input_layout)))));
        password.check(matches(isEnabled()));
        ViewInteraction button = onView(
                allOf(withId(R.id.enter_button), withText("SIGN IN"), withContentDescription("Save"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button.check(matches(isClickable()));
        button.perform(click());
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Login and password cannot be empty")));

        SystemClock.sleep(1000);
        login.perform(typeText(" "));
        password.perform(typeText(" "));
        button.perform(click());
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Login and password cannot be empty")));

        SystemClock.sleep(1000);
        login.perform(clearText(), typeText("123"));
        password.perform(clearText(), typeText("123"));
        button.perform(click());
        onView(withText(R.string.wrong_login_or_password))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Wrong login or password")));
    }

    @Test
    public void signInOK() {
        SystemClock.sleep(8000);
        ViewInteraction textView = onView(
                allOf(withText("Authorization"),
                        withParent(withParent(withId(R.id.nav_host_fragment)))));
        textView.check(matches(isDisplayed()));
        ViewInteraction login = onView(
                allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))));
        login.check(matches(isEnabled()));
        ViewInteraction password = onView(
                allOf(withHint("Password"),
                        withParent(withParent(withId(R.id.password_text_input_layout)))));
        password.check(matches(isEnabled()));
        ViewInteraction button = onView(
                allOf(withId(R.id.enter_button), withText("SIGN IN"), withContentDescription("Save"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button.check(matches(isClickable()));

        login.perform(typeText("login2"));
        password.perform(typeText("password2"));
        button.perform(click());
        SystemClock.sleep(2000);
        ViewInteraction news = onView((withText("News")));
        news.check(matches(isDisplayed()));
        ViewInteraction claims = onView((withText("Claims")));
        claims.check(matches(isDisplayed()));
        ViewInteraction man = onView((withId(R.id.authorization_image_button)));
        man.perform(click());
        ViewInteraction exitButton = onView((withText("Log out")));
        exitButton.perform(click());
        SystemClock.sleep(2000);
    }

    //    todo Категория. "Можно объединить несколько тестов в категорию, например medium (Чтобы запустить их разом)"
    @Test
    public void expandAll() {
//        login();
        SystemClock.sleep(12000);
        ViewInteraction exNews = onView(
                allOf(withId(R.id.expand_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_news_include_on_fragment_main),
                                        0),
                                4),
                        isDisplayed()));
        exNews.perform(click());
        ViewInteraction allNews = onView((withId(R.id.all_news_text_view))).check(matches(not(isDisplayed())));
        ViewInteraction exClaims = onView(
                allOf(withId(R.id.expand_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include_on_fragment_main),
                                        0),
                                3),
                        isDisplayed()));
        exClaims.perform(click());
        ViewInteraction allClaims = onView((withId(R.id.all_claims_text_view))).check(matches(not(isDisplayed())));

        exNews.perform(click());
        allNews.check(matches(isDisplayed()));
        exClaims.perform(click());
        allClaims.check(matches(isDisplayed()));
        SystemClock.sleep(2000);
    }

    @Test
    public void openAllNews() {
        SystemClock.sleep(10000);
        ViewInteraction allNews = onView((withId(R.id.all_news_text_view))).perform(click());
        ViewInteraction sortButton = onView((withId(R.id.sort_news_material_button))).check(matches(isDisplayed()));
    }

    @Test
    public void openAllClaims() {
        SystemClock.sleep(10000);
        ViewInteraction allClaims = onView((withId(R.id.all_claims_text_view))).perform(click());
        ViewInteraction addNewClaimButton = onView((withId(R.id.add_new_claim_material_button))).check(matches(isDisplayed()));
    }

    @Test
    public void expandSingleNews() {
        SystemClock.sleep(6000);
        ViewInteraction expandNews = onView(
                allOf(withId(R.id.news_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_news_cards_block_constraint_layout),
                                0)));
        expandNews.perform(actionOnItemAtPosition(0, click()));
        SystemClock.sleep(2000);
        ViewInteraction newsDescription = onView(withIndex(withId(R.id.view_news_item_image_view), 0));
        newsDescription.check(matches(isDisplayed()));

        onView(withIndex(withId(R.id.category_icon_image_view), 0)).perform(click());
        ViewInteraction newsDescription2 = onView(withIndex(withId(R.id.view_news_item_image_view), 0))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void openSingleClaim() {
        SystemClock.sleep(6000);
        ViewInteraction firstClaim = onView(
                allOf(withIndex(withId(R.id.executor_name_material_text_view), 0)));
        firstClaim.perform(click());

        ViewInteraction status = onView(
                allOf(withId(R.id.status_label_text_view), withText("In progress"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        status.check(matches(isDisplayed()));
        ViewInteraction backButton = onView(withId(R.id.close_image_button)).perform(nestedScrollTo());
        ViewInteraction buttonAddComment = onView(
                allOf(withId(R.id.add_comment_image_button), withContentDescription("button add comment"),
                        withParent(withParent(withId(R.id.comments_material_card_view))),
                        isDisplayed()));
        buttonAddComment.check(matches(isDisplayed()));

        backButton.perform(click());
        ViewInteraction allNews = onView((withId(R.id.all_news_text_view))).check(matches(isDisplayed()));
    }

    @Test
    public void createClaim() {
        SystemClock.sleep(7000);
        String claimTitleString = "Прувет Орл";
        ViewInteraction buttonCreateClaim = onView(withId(R.id.add_new_claim_material_button)).perform(click());

        ViewInteraction title = onView(withId(R.id.custom_app_bar_title_text_view)).check(matches(withText("Creating")));
        ViewInteraction subTitle = onView(withId(R.id.custom_app_bar_sub_title_text_view)).check(matches(withText("Claims")));

        ViewInteraction claimTitle = onView(withId(R.id.title_edit_text)).perform(replaceText("Здравствуйте, я ваша тетя и я не влезу в это поле ввода"));
        claimTitle.check(matches(withText("Здравствуйте, я ваша тетя и я не влезу в это поле ")));

        onView(withId(R.id.save_button)).perform(click());
        onView(withText("Fill empty fields")).check(matches(isDisplayed()));
        onView(withText("OK")).perform(click());

        claimTitle.perform(clearText(), replaceText(claimTitleString));
        onView(withId(R.id.executor_drop_menu_auto_complete_text_view)).perform(click());

        SystemClock.sleep(2000);
        claimTitle.perform(click());

        onView(withId(R.id.date_in_plan_text_input_edit_text)).perform(replaceText("09.04.2022"));
        onView(withId(R.id.time_in_plan_text_input_edit_text)).perform(replaceText("09:10"));
        onView(withId(R.id.description_edit_text)).perform(replaceText("Некое описание"));

        SystemClock.sleep(2000);
        onView(withId(R.id.description_edit_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.cancel_button)).perform(click());
        onView(withText("CANCEL")).perform(click());
        title.check(matches(isDisplayed()));

        onView(withId(R.id.cancel_button)).perform(click());
        onView(withText("OK")).perform(click());
        ViewInteraction news = onView((withText("News")));
        news.check(matches(isDisplayed()));

        buttonCreateClaim.perform(click());
        title.check(matches(isDisplayed()));
        subTitle.check(matches(isDisplayed()));
        claimTitle.perform(replaceText(claimTitleString));
        onView(withId(R.id.executor_drop_menu_auto_complete_text_view)).perform(click());
        SystemClock.sleep(2000);
        claimTitle.perform(click());
        onView(withId(R.id.date_in_plan_text_input_edit_text)).perform(replaceText("09.04.2022"));
        onView(withId(R.id.time_in_plan_text_input_edit_text)).perform(replaceText("09:10"));
        onView(withId(R.id.description_edit_text)).perform(replaceText("Некое описание"));
        onView(withId(R.id.description_edit_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());

        ViewInteraction allClaims = onView((withId(R.id.all_claims_text_view))).perform(click());
        if (isDisplayedWithSwipe(claimTitleString, 2)) {
            onView(withText(claimTitleString)).check(matches(isDisplayed()));
        }
        ;
    }

    public void checkClaimStatus(String status) {
        ViewInteraction firstClaim = onView(
                allOf(withIndex(withId(R.id.executor_name_material_text_view), 0)));
        firstClaim.perform(click());
        ViewInteraction claimStatus = onView(
                allOf(withId(R.id.status_label_text_view),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class)))));
        claimStatus.check(matches(allOf(isDisplayed(), withText(status))));
        ViewInteraction backButton = onView(withId(R.id.close_image_button)).perform(nestedScrollTo());
        backButton.perform(click());
    }

    @Test
    public void filteringClaims() {
        SystemClock.sleep(6000);
        onView((withId(R.id.all_claims_text_view))).perform(click());
        ViewInteraction buttonFiltering = onView((withId(R.id.filters_material_button))).perform(click());
        ViewInteraction titleFiltering = onView((withId(R.id.claim_filter_dialog_title))).check(matches(isDisplayed()));
        ViewInteraction inProgress = onView((withId(R.id.item_filter_in_progress))).perform(click());
        ViewInteraction buttonCancel = onView((withId(R.id.claim_filter_cancel_material_button))).perform(click());
        buttonFiltering.perform(click());
        inProgress.check(matches(isChecked()));

        inProgress.perform(click());
        ViewInteraction buttonOk = onView((withId(R.id.claim_list_filter_ok_material_button))).perform(click());
        checkClaimStatus("Open");
        ViewInteraction claims = onView(withText("Claims")).check(matches(isDisplayed()));

        buttonFiltering.perform(click());
        ViewInteraction open = onView((withId(R.id.item_filter_open))).perform(click());
        open.check(matches(isNotChecked()));
        inProgress.perform(click());
        inProgress.check(matches(isChecked()));
        buttonOk.perform(click());
        checkClaimStatus("In progress");
        claims.check(matches(isDisplayed()));

        buttonFiltering.perform(click());
        ViewInteraction executed = onView((withId(R.id.item_filter_executed))).perform(click());
        executed.check(matches(isChecked()));
        inProgress.perform(click());
        inProgress.check(matches(isNotChecked()));
        buttonOk.perform(click());
        checkClaimStatus("Executed");
        claims.check(matches(isDisplayed()));

        buttonFiltering.perform(click());
        ViewInteraction cancelled = onView((withId(R.id.item_filter_cancelled))).perform(click());
        cancelled.check(matches(isChecked()));
        executed.perform(click());
        executed.check(matches(isNotChecked()));
        buttonOk.perform(click());
        checkClaimStatus("Canceled");
        claims.check(matches(isDisplayed()));
    }

    @Test
    public void claimScreen() {
        SystemClock.sleep(6000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("Claims")).perform(click());
        onView(withText("Claims")).check(matches(isDisplayed()));

        onView(withId(R.id.add_new_claim_material_button)).perform(click());
        onView(withId(R.id.custom_app_bar_title_text_view)).check(matches(withText("Creating")));
        onView(withId(R.id.custom_app_bar_sub_title_text_view)).check(matches(withText("Claims")));
    }

    @Test
    public void newsScreenSorting() {
        SystemClock.sleep(6000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("News")).perform(click());
        onView(withText("News")).check(matches(isDisplayed()));

        String firstNews = TextHelpers.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
        ViewInteraction buttonSort = onView(withId(R.id.sort_news_material_button)).perform(click());
        String lastNews = TextHelpers.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
        buttonSort.perform(click());
        String firstNewsAgain = TextHelpers.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
        assertEquals(firstNews, firstNewsAgain);
        assertNotEquals(firstNews, lastNews);
    }

    @Test
    public void controlPanelSorting() {
        SystemClock.sleep(6000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("News")).perform(click());
        onView(withText("News")).check(matches(isDisplayed()));

        onView(withId(R.id.edit_news_material_button)).perform(click());
        onView(withText("Control panel")).check(matches(isDisplayed()));

        String firstNews = TextHelpers.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
        ViewInteraction buttonSort = onView(withId(R.id.sort_news_material_button)).perform(click());
        String lastNews = TextHelpers.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
        buttonSort.perform(click());
        String firstNewsAgain = TextHelpers.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
        assertEquals(firstNews, firstNewsAgain);
        assertNotEquals(firstNews, lastNews);
    }

    String newsTitle = "Некий заголовок";
    String newsDescription = "Пробе пера";
    String newsPublicationDate = "07.04.2022";

    @Test
    public void controlPanelCreateNews() {
        SystemClock.sleep(6000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("News")).perform(click());
        onView(withText("News")).check(matches(isDisplayed()));

        onView(withId(R.id.edit_news_material_button)).perform(click());
        onView(withText("Control panel")).check(matches(isDisplayed()));

        onView(withId(R.id.add_news_image_view)).perform(click());
        onView(withId(R.id.custom_app_bar_title_text_view)).check(matches(withText("Creating")));
        onView(withId(R.id.custom_app_bar_sub_title_text_view)).check(matches(withText("News")));

        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click());
        onView(withId(R.id.news_item_title_text_input_edit_text)).perform(click());
        onView(withId(R.id.news_item_title_text_input_edit_text)).perform(replaceText(newsTitle),
                closeSoftKeyboard());
        onView(withId(R.id.cancel_button)).perform(click());
        onView(withText("Cancel")).perform(click());
        onView(withId(R.id.news_item_title_text_input_edit_text)).check(matches(withText(newsTitle)));

        onView(withId(R.id.cancel_button)).perform(click());
        onView(withText("OK")).perform(click());
        onView(withText("Control panel")).check(matches(isDisplayed()));

        onView(withId(R.id.add_news_image_view)).perform(click());
        onView(withId(R.id.custom_app_bar_title_text_view)).check(matches(withText("Creating")));
        onView(withId(R.id.custom_app_bar_sub_title_text_view)).check(matches(withText("News")));
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click());
        onView(withId(R.id.news_item_title_text_input_edit_text)).perform(click());
        onView(withId(R.id.news_item_title_text_input_edit_text)).perform(replaceText(newsTitle));
        onView(withId(R.id.news_item_publish_date_text_input_edit_text)).perform(replaceText(newsPublicationDate));
        onView(withId(R.id.news_item_publish_time_text_input_edit_text)).perform(replaceText("07:22"));
        onView(withId(R.id.news_item_description_text_input_edit_text)).perform(replaceText(newsDescription),
                closeSoftKeyboard());
        onView(withId(R.id.switcher)).check(matches(allOf(withText("Active"), isDisplayed())));

        onView(withId(R.id.save_button)).perform(click());
        onView(withText("Control panel")).check(matches(isDisplayed()));
        boolean invis = true;
        int n = 2;
        while (invis) {
            try {
                onView(withText(newsTitle)).check(matches(isDisplayed()));
                invis = false;
            } catch (NoMatchingViewException ignored) {
            }
            onView(allOf(withId(R.id.news_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(n, swipeUp()));
            n = n + 2;
        }
        ;
        onView(withText(newsTitle)).check(matches(isDisplayed()));
    }


    @Test
    public void newsScreenFiltering() {
        SystemClock.sleep(6000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("News")).perform(click());
        onView(withText("News")).check(matches(isDisplayed()));

        onView(withId(R.id.filter_news_material_button)).perform(click());
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(replaceText(newsPublicationDate));
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(replaceText(newsPublicationDate));
        onView(withId(R.id.filter_button)).perform(click());

        onView(withIndex(withId(R.id.news_item_date_text_view), 0)).check(matches(withText(newsPublicationDate)));

        onView(withId(R.id.edit_news_material_button)).perform(click());
        onView(withText("Control panel")).check(matches(isDisplayed()));

        onView(withId(R.id.filter_news_material_button)).perform(click());
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(replaceText(newsPublicationDate));
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(replaceText(newsPublicationDate));
        onView(withId(R.id.filter_button)).perform(click());

        onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)).check(matches(withText(newsPublicationDate)));

        onView(withIndex(withId(R.id.edit_news_item_image_view), 0)).perform(click());
        onView(withId(R.id.switcher)).perform(click());
        onView(withId(R.id.save_button)).perform(click());

        onView(withId(R.id.filter_news_material_button)).perform(click());
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(replaceText(newsPublicationDate));
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(replaceText(newsPublicationDate));
        onView(withId(R.id.filter_news_active_material_check_box)).perform(click()).check(matches(isNotChecked()));
        onView(withId(R.id.filter_news_inactive_material_check_box)).check(matches(isChecked()));
        onView(withId(R.id.filter_button)).perform(click());

        onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)).check(matches(withText(newsPublicationDate)));
        onView(withIndex(withId(R.id.news_item_published_text_view), 0)).check(matches(withText("Not active")));

        onView(withIndex(withId(R.id.edit_news_item_image_view), 0)).perform(click());
        onView(withId(R.id.switcher)).perform(click());
        onView(withId(R.id.save_button)).perform(click());

        onView(withId(R.id.filter_news_material_button)).perform(click());
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(replaceText(newsPublicationDate));
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(replaceText(newsPublicationDate));
        onView(withId(R.id.filter_news_active_material_check_box)).check(matches(isChecked()));
        onView(withId(R.id.filter_news_inactive_material_check_box)).perform(click()).check(matches(isNotChecked()));
        onView(withId(R.id.filter_button)).perform(click());

        onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)).check(matches(withText(newsPublicationDate)));
        onView(withIndex(withId(R.id.news_item_published_text_view), 0)).check(matches(withText("Active")));
    }

    public boolean isDisplayedWithSwipe(String locator, int recycler) {
        try {
            onView(withText(locator)).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException ignored) {
        }
        boolean invis = true;
        int n = 1;
        while (invis) {
            try {
                if (recycler == 1) {
                    onView(allOf(withId(R.id.news_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(n, swipeUp()));
                } else {
                    onView(allOf(withId(R.id.claim_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(n, swipeUp()));
                }
            } catch (PerformException e) {
                return false;
            }
            try {
                onView(withText(locator)).check(matches(isDisplayed()));
                invis = false;
            } catch (NoMatchingViewException e) {
                invis = true;
            }
            n = n + 1;
            if (n > 400) {
                return false;
            }
        }
        ;
        return true;
    }

    @Test
    public void newsEditingDeleting() {
        SystemClock.sleep(6000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("News")).perform(click());
        onView(withText("News")).check(matches(isDisplayed()));

        onView(withId(R.id.edit_news_material_button)).perform(click());
        onView(withText("Control panel")).check(matches(isDisplayed()));

        if (isDisplayedWithSwipe(newsTitle, 1)) {
            onView(withText(newsTitle)).check(matches(isDisplayed())).perform(click());
        }
        onView(allOf(withId(R.id.news_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(2, swipeUp()));
        String result = TextHelpers.getText(onView(withText(newsDescription)));
        assertEquals(result, newsDescription);
        onView(withText(newsTitle)).check(matches(isDisplayed())).perform(click());
        SystemClock.sleep(1500);
        onView(withText(newsDescription)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(newsTitle)))))))).perform(click());
        onView(withId(R.id.custom_app_bar_title_text_view)).check(matches(withText("Editing")));
        onView(withId(R.id.custom_app_bar_sub_title_text_view)).check(matches(withText("News")));
        onView(withId(R.id.news_item_title_text_input_edit_text)).check(matches(withText(newsTitle)));
        String newNewsTitle = "Чудо чудесное";
        onView(withId(R.id.news_item_title_text_input_edit_text)).perform(replaceText(newNewsTitle));
        onView(withId(R.id.save_button)).perform(click());

        onView(withText("Control panel")).check(matches(isDisplayed()));
        if (isDisplayedWithSwipe(newNewsTitle, 1)) {
            onView(withText(newNewsTitle)).check(matches(isDisplayed()));
        }
        onView(allOf(withId(R.id.news_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(2, swipeUp()));

        onView(allOf(withId(R.id.delete_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(newNewsTitle)))))))).perform(click());
        onView(withText("OK")).perform(click());
        SystemClock.sleep(1500);
        if (isDisplayedWithSwipe(newNewsTitle, 1)) {
            onView(withText(newNewsTitle)).check(matches(not(isDisplayed())));
        }
    }

    public String getCurrentDate() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

    @Test
    public void aboutScreenAndBackToMain() {
        SystemClock.sleep(6000);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("About")).perform(click());
        onView(withId(R.id.about_version_title_text_view)).check(matches(allOf(withText("Version:"), isDisplayed())));
        onView(withId(R.id.about_version_value_text_view)).check(matches(allOf(withText("1.0.0"), isDisplayed())));
        onView(withId(R.id.about_privacy_policy_value_text_view)).check(matches(allOf(withText("https://vhospice.org/#/privacy-policy/"), isDisplayed(), isClickable())));
        onView(withId(R.id.about_terms_of_use_label_text_view)).check(matches(allOf(withText("Terms of use:"), isDisplayed())));
        onView(withId(R.id.about_terms_of_use_value_text_view)).check(matches(allOf(withText("https://vhospice.org/#/terms-of-use"), isDisplayed(), isClickable())));
        onView(withId(R.id.about_company_info_label_text_view)).check(matches(allOf(withText("© I-Teco, 2022"), isDisplayed())));

        onView(withId(R.id.about_back_image_button)).perform(click());
        onView(withText("News")).check(matches(isDisplayed()));
        onView(withText("Claims")).check(matches(isDisplayed()));
    }

    @Test
    public void thematicQuotes() {
        SystemClock.sleep(6000);
        onView(withId(R.id.our_mission_image_button)).perform(click());
        onView(withId(R.id.our_mission_title_text_view)).check(matches(allOf(withText("Love is all"), isDisplayed())));
        onView(withIndex(withId(R.id.our_mission_item_image_view), 0)).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.our_mission_item_title_text_view), 0)).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.our_mission_item_title_text_view), 0)).perform(click());
        onView(withIndex(withId(R.id.our_mission_item_description_text_view), 0)).check(matches(isDisplayed()));

        onView(withIndex(withId(R.id.our_mission_item_title_text_view), 0)).perform(click());
        onView(withIndex(withId(R.id.our_mission_item_description_text_view), 0)).check(matches(not(isDisplayed())));
        
    }
}
