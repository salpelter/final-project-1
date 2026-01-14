package ge.tbc.testautomation.steps;

import com.codeborne.selenide.ex.ElementNotFound;
import ge.tbc.testautomation.constants.Urls;
import ge.tbc.testautomation.pages.CommonPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CommonSteps {
    CommonPage commonPage = new CommonPage();

    public CommonSteps openMenu(boolean isMobile) {
        if (isMobile) {
            commonPage.mobileHamburgerMenuIcon.shouldBe(visible).click();
        }
        else {
            commonPage.personalMegaMenu.shouldBe(visible).hover();
        }

        return this;
    }

    public CommonSteps clickOnExchangeRatesPageLink(Boolean isMobile) {
        if (isMobile) {
            commonPage.mobileMegaMenuExchangeRatesLink.shouldBe(visible).click();
        }
        else {
            commonPage.megaMenuExchangeRatesLink.shouldBe(visible).click();
        }

        return this;
    }

    public CommonSteps clickOnAddressesPageLink(Boolean isMobile) {
        if (isMobile) {
            commonPage.mobileMegaMenuAddressesLink.shouldBe(visible).click();
        }
        else {
            commonPage.megaMenuAddressesLink.shouldBe(visible).click();
        }

        return this;
    }

    public CommonSteps clickOnOffersPageLink(Boolean isMobile) {
        if (isMobile) {
            commonPage.mobileMegaMenuOffersLink.shouldBe(visible).click();
        }
        else {
            commonPage.megaMenuOffersLink.shouldBe(visible).click();
        }

        return this;
    }

    public CommonSteps verifyDenyCookiesButtonVisibility() {
        commonPage.denyCookiesButton.shouldBe(visible, Duration.ofSeconds(5));

        return this;
    }

    public CommonSteps clickOnDenyCookiesButton() {
        commonPage.denyCookiesButton.click();

        return this;
    }
}
