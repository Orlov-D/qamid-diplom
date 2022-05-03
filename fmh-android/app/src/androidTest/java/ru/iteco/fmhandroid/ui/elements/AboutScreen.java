package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutScreen {
    public ViewInteraction versionTitle = onView(withId(R.id.about_version_title_text_view));
    public ViewInteraction version = onView(withId(R.id.about_version_value_text_view));
    public ViewInteraction privacyPolicy = onView(withId(R.id.about_privacy_policy_value_text_view));
    public ViewInteraction termsTitle = onView(withId(R.id.about_terms_of_use_label_text_view));
    public ViewInteraction termsUrl = onView(withId(R.id.about_terms_of_use_value_text_view));
    public ViewInteraction copyright = onView(withId(R.id.about_company_info_label_text_view));
    public ViewInteraction buttonBack = onView(withId(R.id.about_back_image_button));

}
