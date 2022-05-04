package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.utils.Utils.TextHelpers;
import static ru.iteco.fmhandroid.ui.utils.Utils.checkClaimStatus;
import static ru.iteco.fmhandroid.ui.utils.Utils.getCurrentDate;
import static ru.iteco.fmhandroid.ui.utils.Utils.getCurrentTime;
import static ru.iteco.fmhandroid.ui.utils.Utils.isDisplayedWithSwipe;
import static ru.iteco.fmhandroid.ui.utils.Utils.nestedScrollTo;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.NoSuchElementException;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.AboutScreen;
import ru.iteco.fmhandroid.ui.elements.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.elements.ClaimScreen;
import ru.iteco.fmhandroid.ui.elements.CommonElements;
import ru.iteco.fmhandroid.ui.elements.ControlPanel;
import ru.iteco.fmhandroid.ui.elements.CreateClaimScreen;
import ru.iteco.fmhandroid.ui.elements.CreateNewsScreen;
import ru.iteco.fmhandroid.ui.elements.EditClaimScreen;
import ru.iteco.fmhandroid.ui.elements.MainScreen;
import ru.iteco.fmhandroid.ui.elements.NewsFilterScreen;
import ru.iteco.fmhandroid.ui.elements.NewsScreen;
import ru.iteco.fmhandroid.ui.elements.ThematicQuotes;

@RunWith(AllureAndroidJUnit4.class)
public class AppActivityTest {
    NewsScreen NewsScreen = new NewsScreen();
    MainScreen MainScreen = new MainScreen();
    AuthorizationScreen AuthorizationScreen = new AuthorizationScreen();
    EditClaimScreen EditClaimScreen = new EditClaimScreen();
    CreateClaimScreen CreateClaimScreen = new CreateClaimScreen();
    CommonElements CommonElements = new CommonElements();
    ClaimScreen ClaimScreen = new ClaimScreen();
    ControlPanel ControlPanel = new ControlPanel();
    CreateNewsScreen CreateNewsScreen = new CreateNewsScreen();
    NewsFilterScreen NewsFilterScreen = new NewsFilterScreen();
    AboutScreen AboutScreen = new AboutScreen();
    ThematicQuotes ThematicQuotes = new ThematicQuotes();


    public static String newsTitle = "Некий заголовок";
    public static String newsDescriptionString = "Пробе пера";
    public static String newNewsTitle = "Чудо чудесное";
    String newsPublicationDate = "07.04.2022";

    @Rule
    public ActivityTestRule<AppActivity> mActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void loginCheck() {
        SystemClock.sleep(7000);
        try {
            AuthorizationScreen.authorization.check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationScreen.login.check(matches(isEnabled()));
        AuthorizationScreen.password.check(matches(isEnabled()));
        AuthorizationScreen.buttonSignIn.check(matches(isClickable()));
        AuthorizationScreen.login.perform(typeText("login2"));
        AuthorizationScreen.password.perform(typeText("password2"));
        AuthorizationScreen.buttonSignIn.perform(click());
        SystemClock.sleep(2000);
    }


    @Test
    public void expandAll() {
        MainScreen.expandAllNews.check(matches(isDisplayed()));
        MainScreen.expandAllNews.perform(click());
        MainScreen.allNews.check(matches(not(isDisplayed())));
        MainScreen.expandClaims.check(matches(isDisplayed()));
        MainScreen.expandClaims.perform(click());
        MainScreen.allClaims.check(matches(not(isDisplayed())));

        MainScreen.expandAllNews.perform(click());
        MainScreen.allNews.check(matches(isDisplayed()));
        MainScreen.expandClaims.perform(click());
        MainScreen.allClaims.check(matches(isDisplayed()));
    }

    @Test
    public void openAllNews() {
        MainScreen.allNews.perform(click());
        NewsScreen.buttonSort.check(matches(isDisplayed()));
    }

    @Test
    public void openAllClaims() {
        MainScreen.allClaims.perform(click());
        MainScreen.addNewClaimButton.check(matches(isDisplayed()));
        MainScreen.allNews.check(doesNotExist());
    }

    @Test
    public void expandSingleNews() {
        MainScreen.expandSingleNews.perform(actionOnItemAtPosition(0, click()));
        MainScreen.newsDescription.check(matches(isDisplayed()));
        MainScreen.categoryIcon.perform(click());
        MainScreen.newsDescriptionAfterCollapse.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void openSingleClaim() {
        MainScreen.firstClaimExecutorName.perform(click());
        EditClaimScreen.claimStatus.check(matches(isDisplayed()));
        EditClaimScreen.backButton.perform(nestedScrollTo());
        EditClaimScreen.buttonAddComment.check(matches(isDisplayed()));
        EditClaimScreen.backButton.perform(click());
        MainScreen.allNews.check(matches(isDisplayed()));
    }

    @Test
    public void createClaim() {
        String claimTitleString = "Прувет Орл " + getCurrentDate() + "T" + getCurrentTime();
        String newClaimTitleString = "Некое описание " + getCurrentDate();
        String currentDate = getCurrentDate();
        String currentTime = getCurrentTime();
        MainScreen.addNewClaimButton.perform(click());
        SystemClock.sleep(2000);

        CreateClaimScreen.title.check(matches(withText("Creating")));
        CreateClaimScreen.subTitle.check(matches(withText("Claims")));

        CreateClaimScreen.claimTitle.perform(replaceText("Здравствуйте, я ваша тетя и я не влезу в это поле ввода"));
        CreateClaimScreen.claimTitle.check(matches(withText("Здравствуйте, я ваша тетя и я не влезу в это поле ")));

        CommonElements.buttonSave.perform(click());
        CreateClaimScreen.toastEmptyFields.check(matches(isDisplayed()));
        CommonElements.buttonOkText.perform(click());

        CreateClaimScreen.claimTitle.perform(clearText(), replaceText(claimTitleString));
        CreateClaimScreen.executorList.perform(click());
        SystemClock.sleep(2000);
        CreateClaimScreen.claimTitle.perform(click());

        CreateClaimScreen.claimDate.perform(replaceText(currentDate));
        CreateClaimScreen.claimTime.perform(replaceText(currentTime));
        CreateClaimScreen.claimDescription.perform(replaceText(newClaimTitleString));

        SystemClock.sleep(2000);
        CreateClaimScreen.claimDescription.perform(closeSoftKeyboard());
        CommonElements.buttonCancel.perform(click());
        CommonElements.buttonCancelText.perform(click());
        CreateClaimScreen.title.check(matches(isDisplayed()));

        CommonElements.buttonCancel.perform(click());
        CommonElements.buttonOkText.perform(click());
        MainScreen.news.check(matches(isDisplayed()));

        MainScreen.addNewClaimButton.perform(click());
        CreateClaimScreen.title.check(matches(isDisplayed()));
        CreateClaimScreen.subTitle.check(matches(isDisplayed()));
        CreateClaimScreen.claimTitle.perform(replaceText(claimTitleString));
        CreateClaimScreen.executorList.perform(click());
        SystemClock.sleep(2000);
        CreateClaimScreen.claimTitle.perform(click());
        CreateClaimScreen.claimDate.perform(replaceText(currentDate));
        CreateClaimScreen.claimTime.perform(replaceText(currentTime));
        CreateClaimScreen.claimDescription.perform(replaceText(newClaimTitleString));
        CreateClaimScreen.claimDescription.perform(closeSoftKeyboard());
        CommonElements.buttonSave.perform(click());

        MainScreen.allClaims.perform(click());

        if (isDisplayedWithSwipe(onView(withText(claimTitleString)), 2, true)) {
            onView(withText(claimTitleString)).check(matches(isDisplayed()));
        } else {
            throw new NoSuchElementException("Not found " + onView(withText(claimTitleString)).toString());
        }
        ;
    }

    @Test
    public void filteringClaims() {
        MainScreen.allClaims.perform(click());
        ClaimScreen.buttonFiltering.perform(click());
        ClaimScreen.titleFiltering.check(matches(isDisplayed()));
        ClaimScreen.inProgress.perform(click());
        ClaimScreen.buttonCancel.perform(click());
        ClaimScreen.buttonFiltering.perform(click());
        ClaimScreen.inProgress.check(matches(isChecked()));

        ClaimScreen.inProgress.perform(click());
        ClaimScreen.buttonOk.perform(click());
        checkClaimStatus("Open");
        ClaimScreen.claims.check(matches(isDisplayed()));

        ClaimScreen.buttonFiltering.perform(click());
        ClaimScreen.open.perform(click());
        ClaimScreen.open.check(matches(isNotChecked()));
        ClaimScreen.inProgress.perform(click());
        ClaimScreen.inProgress.check(matches(isChecked()));
        ClaimScreen.buttonOk.perform(click());
        checkClaimStatus("In progress");
        ClaimScreen.claims.check(matches(isDisplayed()));

        ClaimScreen.buttonFiltering.perform(click());
        ClaimScreen.executed.perform(click());
        ClaimScreen.executed.check(matches(isChecked()));
        ClaimScreen.inProgress.perform(click());
        ClaimScreen.inProgress.check(matches(isNotChecked()));
        ClaimScreen.buttonOk.perform(click());
        checkClaimStatus("Executed");
        ClaimScreen.claims.check(matches(isDisplayed()));

        ClaimScreen.buttonFiltering.perform(click());
        ClaimScreen.cancelled.perform(click());
        ClaimScreen.cancelled.check(matches(isChecked()));
        ClaimScreen.executed.perform(click());
        ClaimScreen.executed.check(matches(isNotChecked()));
        ClaimScreen.buttonOk.perform(click());
        checkClaimStatus("Canceled");
        ClaimScreen.claims.check(matches(isDisplayed()));
    }

    @Test
    public void claimScreen() {
        CommonElements.mainMenu.perform(click());
        CommonElements.menuClaims.perform(click());
        ClaimScreen.claims.check(matches(isDisplayed()));

        ClaimScreen.addNewClaimButton.perform(click());
        CreateClaimScreen.title.check(matches(withText("Creating")));
        CreateClaimScreen.subTitle.check(matches(withText("Claims")));
    }

    @Test
    public void newsScreenSorting() {
        CommonElements.mainMenu.perform(click());
        CommonElements.menuNews.perform(click());
        NewsScreen.news.check(matches(isDisplayed()));

        String firstNews = TextHelpers.getText(NewsScreen.firstNews);
        NewsScreen.buttonSort.perform(click());
        String lastNews = TextHelpers.getText(NewsScreen.lastNews);
        NewsScreen.buttonSort.perform(click());
        String firstNewsAgain = TextHelpers.getText(NewsScreen.firstNewsAgain);
        assertEquals(firstNews, firstNewsAgain);
        assertNotEquals(firstNews, lastNews);
    }

    @Test
    public void controlPanelSorting() {
        CommonElements.mainMenu.perform(click());
        CommonElements.menuNews.perform(click());
        NewsScreen.news.check(matches(isDisplayed()));

        NewsScreen.buttonControlPanel.perform(click());
        NewsScreen.controlPanel.check(matches(isDisplayed()));

        String firstNews = TextHelpers.getText(NewsScreen.firstNews);
        String firstPublicationDate = TextHelpers.getText(ControlPanel.firstPublicationDate);
        String firstCreationDate = TextHelpers.getText(ControlPanel.firstCreationDate);
        NewsScreen.buttonSort.perform(click());
        String lastPublicationDate = TextHelpers.getText(ControlPanel.lastPublicationDate);
        NewsScreen.buttonSort.perform(click());
        String firstNewsAgain = TextHelpers.getText(NewsScreen.firstNewsAgain);
        String firstPublicationDateAgain = TextHelpers.getText(ControlPanel.firstPublicationDateAgain);
        String firstCreationDateAgain = TextHelpers.getText(ControlPanel.firstCreationDateAgain);
        assertEquals(firstNews, firstNewsAgain);
        assertEquals(firstPublicationDate, firstPublicationDateAgain);
        assertEquals(firstCreationDate, firstCreationDateAgain);
        assertNotEquals(firstPublicationDate, lastPublicationDate);
    }

    @Test
    public void controlPanelCreateNews() {
        CommonElements.mainMenu.perform(click());
        CommonElements.menuNews.perform(click());
        NewsScreen.news.check(matches(isDisplayed()));

        NewsScreen.buttonControlPanel.perform(click());
        NewsScreen.controlPanel.check(matches(isDisplayed()));

        ControlPanel.buttonCreateNews.perform(click());
        CreateNewsScreen.title.check(matches(withText("Creating")));
        CreateNewsScreen.subTitle.check(matches(withText("News")));

        CreateNewsScreen.categoryList.perform(click());
        CreateNewsScreen.newsTitle.perform(click());
        CreateNewsScreen.newsTitle.perform(replaceText(newsTitle), closeSoftKeyboard());
        CommonElements.buttonCancel.perform(click());
        CommonElements.buttonCancelText.perform(click());
        CreateNewsScreen.newsTitle.check(matches(withText(newsTitle)));

        CommonElements.buttonCancel.perform(click());
        CommonElements.buttonOkText.perform(click());
        NewsScreen.controlPanel.check(matches(isDisplayed()));

        ControlPanel.buttonCreateNews.perform(click());
        CreateNewsScreen.title.check(matches(withText("Creating")));
        CreateNewsScreen.subTitle.check(matches(withText("News")));
        CreateNewsScreen.categoryList.perform(click());
        CreateNewsScreen.newsTitle.perform(click());
        CreateNewsScreen.newsTitle.perform(replaceText(newsTitle));
        CreateNewsScreen.newsDate.perform(replaceText(newsPublicationDate));
        CreateNewsScreen.newsTime.perform(replaceText("07:22"));
        CreateNewsScreen.newsDescription.perform(replaceText(newsDescriptionString), closeSoftKeyboard());
        CreateNewsScreen.newsSwitcher.check(matches(allOf(withText("Active"), isDisplayed())));

        CommonElements.buttonSave.perform(click());
        NewsScreen.controlPanel.check(matches(isDisplayed()));
        if (isDisplayedWithSwipe(onView(withText(newsTitle)), 1, true)) {
            onView(withText(newsTitle)).check(matches(isDisplayed()));
        }

        onView(allOf(withId(R.id.delete_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(newsTitle)))))))).perform(click());
        CommonElements.buttonOkText.perform(click());
    }


    @Test
    public void newsScreenFiltering() {
        CommonElements.mainMenu.perform(click());
        CommonElements.menuNews.perform(click());
        NewsScreen.news.check(matches(isDisplayed()));

        NewsScreen.buttonControlPanel.perform(click());
        NewsScreen.controlPanel.check(matches(isDisplayed()));

        ControlPanel.buttonCreateNews.perform(click());
        CreateNewsScreen.title.check(matches(withText("Creating")));
        CreateNewsScreen.subTitle.check(matches(withText("News")));
        CreateNewsScreen.categoryList.perform(click());
        CreateNewsScreen.newsTitle.perform(click());
        CreateNewsScreen.newsTitle.perform(replaceText(newsTitle));
        CreateNewsScreen.newsDate.perform(replaceText(newsPublicationDate));
        CreateNewsScreen.newsTime.perform(replaceText("07:22"));
        CreateNewsScreen.newsDescription.perform(replaceText(newsDescriptionString), closeSoftKeyboard());
        CreateNewsScreen.newsSwitcher.check(matches(allOf(withText("Active"), isDisplayed())));

        CommonElements.buttonSave.perform(click());
        NewsScreen.controlPanel.check(matches(isDisplayed()));

        CommonElements.mainMenu.perform(click());
        CommonElements.menuNews.perform(click());
        NewsScreen.news.check(matches(isDisplayed()));

        NewsScreen.buttonFilter.perform(click());
        NewsFilterScreen.publishDateStart.perform(replaceText(newsPublicationDate));
        NewsFilterScreen.publishDateEnd.perform(replaceText(newsPublicationDate));
        NewsFilterScreen.buttonFilter.perform(click());

        NewsScreen.firstNewsDate.check(matches(withText(newsPublicationDate)));

        NewsScreen.buttonControlPanel.perform(click());
        NewsScreen.controlPanel.check(matches(isDisplayed()));

        NewsScreen.buttonFilter.perform(click());
        NewsFilterScreen.publishDateStart.perform(replaceText(newsPublicationDate));
        NewsFilterScreen.publishDateEnd.perform(replaceText(newsPublicationDate));
        NewsFilterScreen.buttonFilter.perform(click());

        ControlPanel.firstPublicationDate.check(matches(withText(newsPublicationDate)));

        ControlPanel.buttonEditNews.perform(click());
        CreateNewsScreen.newsSwitcher.perform(click());
        CommonElements.buttonSave.perform(click());

        NewsScreen.buttonFilter.perform(click());
        NewsFilterScreen.publishDateStart.perform(replaceText(newsPublicationDate));
        NewsFilterScreen.publishDateEnd.perform(replaceText(newsPublicationDate));
        NewsFilterScreen.checkboxActive.perform(click()).check(matches(isNotChecked()));
        NewsFilterScreen.checkboxNotActive.check(matches(isChecked()));
        NewsFilterScreen.buttonFilter.perform(click());

        ControlPanel.firstPublicationDateNotActive.check(matches(withText(newsPublicationDate)));
        ControlPanel.newsStatus.check(matches(withText("Not active")));

        ControlPanel.buttonEditNewsNotActive.perform(click());
        CreateNewsScreen.newsSwitcher.perform(click());
        CommonElements.buttonSave.perform(click());

        NewsScreen.buttonFilter.perform(click());
        NewsFilterScreen.publishDateStart.perform(replaceText(newsPublicationDate));
        NewsFilterScreen.publishDateEnd.perform(replaceText(newsPublicationDate));
        NewsFilterScreen.checkboxActive.check(matches(isChecked()));
        NewsFilterScreen.checkboxNotActive.perform(click()).check(matches(isNotChecked()));
        NewsFilterScreen.buttonFilter.perform(click());

        ControlPanel.firstPublicationDateActive.check(matches(withText(newsPublicationDate)));
        ControlPanel.newsStatusActive.check(matches(withText("Active")));

        ControlPanel.buttonDeleteNews.perform(click());
        CommonElements.buttonOkText.perform(click());
    }

    @Test
    public void newsEditingDeleting() {
        CommonElements.mainMenu.perform(click());
        CommonElements.menuNews.perform(click());
        NewsScreen.news.check(matches(isDisplayed()));

        NewsScreen.buttonControlPanel.perform(click());
        NewsScreen.controlPanel.check(matches(isDisplayed()));

        ControlPanel.buttonCreateNews.perform(click());
        CreateNewsScreen.title.check(matches(withText("Creating")));
        CreateNewsScreen.subTitle.check(matches(withText("News")));
        CreateNewsScreen.categoryList.perform(click());
        CreateNewsScreen.newsTitle.perform(click());
        CreateNewsScreen.newsTitle.perform(replaceText(newsTitle));
        CreateNewsScreen.newsDate.perform(replaceText(newsPublicationDate));
        CreateNewsScreen.newsTime.perform(replaceText("07:22"));
        CreateNewsScreen.newsDescription.perform(replaceText(newsDescriptionString), closeSoftKeyboard());
        CreateNewsScreen.newsSwitcher.check(matches(allOf(withText("Active"), isDisplayed())));

        CommonElements.buttonSave.perform(click());
        NewsScreen.controlPanel.check(matches(isDisplayed()));

        if (isDisplayedWithSwipe(onView(withText(newsTitle)), 1, true)) {
            onView(withText(newsTitle)).check(matches(isDisplayed())).perform(click());
        }

        ControlPanel.newsDescription.check(matches(isDisplayed()));
        onView(withText(newsTitle)).perform(click());
        SystemClock.sleep(1500);
        ControlPanel.newsDescription.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        ControlPanel.newsEdit.perform(click());
        CreateNewsScreen.title.check(matches(withText("Editing")));
        CreateNewsScreen.subTitle.check(matches(withText("News")));
        CreateNewsScreen.newsTitle.check(matches(withText(newsTitle)));
        CreateNewsScreen.newsTitle.perform(replaceText(newNewsTitle));
        CommonElements.buttonSave.perform(click());

        NewsScreen.controlPanel.check(matches(isDisplayed()));
        if (isDisplayedWithSwipe(onView(withText(newNewsTitle)), 1, true)) {
            onView(withText(newNewsTitle)).check(matches(isDisplayed()));
        }

        ControlPanel.newsDelete.perform(click());
        CommonElements.buttonOkText.perform(click());
        SystemClock.sleep(1500);
        if (isDisplayedWithSwipe(onView(withText(newNewsTitle)), 1, false)) {
            throw new NoSuchElementException("Not delete!");
        }
    }

    @Test
    public void aboutScreenAndBackToMain() {
        CommonElements.mainMenu.perform(click());
        CommonElements.menuAbout.perform(click());
        AboutScreen.versionTitle.check(matches(allOf(withText("Version:"), isDisplayed())));
        AboutScreen.version.check(matches(allOf(withText("1.0.0"), isDisplayed())));
        AboutScreen.privacyPolicy.check(matches(allOf(withText("https://vhospice.org/#/privacy-policy/"), isDisplayed(), isClickable())));
        AboutScreen.termsTitle.check(matches(allOf(withText("Terms of use:"), isDisplayed())));
        AboutScreen.termsUrl.check(matches(allOf(withText("https://vhospice.org/#/terms-of-use"), isDisplayed(), isClickable())));
        AboutScreen.copyright.check(matches(allOf(withText("© I-Teco, 2022"), isDisplayed())));

        AboutScreen.buttonBack.perform(click());
        MainScreen.allNews.check(matches(isDisplayed()));
        MainScreen.allClaims.check(matches(isDisplayed()));
    }

    @Test
    public void thematicQuotes() {
        CommonElements.thematicQuotes.perform(click());
        ThematicQuotes.title.check(matches(allOf(withText("Love is all"), isDisplayed())));
        ThematicQuotes.icon.check(matches(isDisplayed()));
        ThematicQuotes.thematicTitle.check(matches(isDisplayed()));
        ThematicQuotes.thematicTitleClickable.perform(click());
        ThematicQuotes.thematicDescription.check(matches(isDisplayed()));

        ThematicQuotes.thematicTitleClickable2.perform(click());
        ThematicQuotes.thematicDescriptionAfterClick.check(matches(not(isDisplayed())));
    }
}
