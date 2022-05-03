package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClaimScreen {
    public ViewInteraction buttonFiltering = onView((withId(R.id.filters_material_button)));
    public ViewInteraction titleFiltering = onView((withId(R.id.claim_filter_dialog_title)));
    public ViewInteraction inProgress = onView((withId(R.id.item_filter_in_progress)));
    public ViewInteraction buttonCancel = onView((withId(R.id.claim_filter_cancel_material_button)));
    public ViewInteraction buttonOk = onView((withId(R.id.claim_list_filter_ok_material_button)));
    public ViewInteraction claims = onView(withText("Claims"));
    public ViewInteraction open = onView((withId(R.id.item_filter_open)));
    public ViewInteraction executed = onView((withId(R.id.item_filter_executed)));
    public ViewInteraction cancelled = onView((withId(R.id.item_filter_cancelled)));
    public ViewInteraction addNewClaimButton = onView((withId(R.id.add_new_claim_material_button)));


}
