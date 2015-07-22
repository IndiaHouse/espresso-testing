package espresso.tasting.webview;

import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tasting.espresso.R;
import tasting.espresso.webview.ShowWebViewActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webContent;
import static android.support.test.espresso.web.matcher.DomMatchers.hasElementWithId;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webKeys;

/**
 * Unit test for {@link ShowWebViewActivity}.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowWebViewActivityTest {

    private static final String EMAIL_TO_BE_TYPED = "email_to_be_entered";

    private static final String PASSWORD_TO_BE_TYPED = "password_to_be_entered";

    @Rule
    public ActivityTestRule<ShowWebViewActivity> activityRule = new ActivityTestRule<>(
            ShowWebViewActivity.class);

    @Test
    public void initial_state() {
        onWebView().check(webContent(hasElementWithId("email_input")));
        onWebView().check(webContent(hasElementWithId("password_input")));
        onWebView().check(webContent(hasElementWithId("submit_button")));
    }

    @Test
    public void enter_email_and_password_submit() {
        onWebView().withElement(findElement(Locator.ID, "email_input"))
                .perform(webKeys(EMAIL_TO_BE_TYPED));
        onWebView().withElement(findElement(Locator.ID, "password_input"))
                .perform(webKeys(PASSWORD_TO_BE_TYPED));
        onWebView().withElement(findElement(Locator.ID, "submit_button"))
                .perform(webClick());
        // This view is in a different Activity, no need to tell Espresso.
        onView(withId(R.id.email)).check(matches(withText(EMAIL_TO_BE_TYPED)));
        onView(withId(R.id.password)).check(matches(withText(PASSWORD_TO_BE_TYPED)));
    }

}