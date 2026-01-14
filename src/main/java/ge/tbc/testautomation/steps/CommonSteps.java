package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.pages.CommonPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class CommonSteps {
    CommonPage commonPage = new CommonPage();

    public CommonSteps clickOnHamburgerMenu() {
        commonPage.mobileHamburgerMenuIcon.click();

        return this;
    }

    public CommonSteps hoverOverPersonalMegaMenu() {
        commonPage.personalMegaMenu.hover();

        return this;
    }

    public CommonSteps clickOnExchangeRatesPageLink(Boolean isMobile) {
        if (isMobile) {
            commonPage.mobileExchangeRatesPageLink.click();
        }
        else {
            commonPage.exchangeRatesPageLink.click();
        }

        return this;
    }

    public CommonSteps clickOnAddressesMenuItem(Boolean isMobile) {
        if (isMobile) {
            commonPage.mobileMegaMenuAddressesItem.click();
        }
        else {
            commonPage.megaMenuAddressesItem.click();
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
