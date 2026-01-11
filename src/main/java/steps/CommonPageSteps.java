package steps;

import pages.CommonPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class CommonPageSteps {
    CommonPage commonPage = new CommonPage();

    public CommonPageSteps verifyDenyCookiesButtonVisibility() {
        commonPage.denyCookiesButton.shouldBe(visible, Duration.ofSeconds(8));

        return this;
    }

    public CommonPageSteps clickOnDenyCookiesButton() {
        commonPage.denyCookiesButton.click();

        return this;
    }
}
