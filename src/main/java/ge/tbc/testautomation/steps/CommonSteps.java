package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.pages.CommonPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

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
            commonPage.mobileMegaMenuExchangeRatesLinks
                    .filter(visible)
                    .first()
                    .click();
        }
        else {
            commonPage.megaMenuExchangeRatesLinks
                    .filter(visible)
                    .first()
                    .click();
        }

        return this;
    }

    public CommonSteps unfoldOtherProductsSection() {
        commonPage.mobileMegaMenuOtherProductsSections
                .filter(visible)
                .first()
                .click();

        return this;
    }

    public CommonSteps clickOnMoneyTransfersPageLink() {
        commonPage.megaMenuMoneyTransfersLinks
                .filter(visible)
                .first()
                .click();

        return this;
    }

    public CommonSteps clickOnAddressesPageLink(Boolean isMobile) {
        if (isMobile) {
            commonPage.mobileMegaMenuAddressesLinks
                    .filter(visible)
                    .first()
                    .click();
        }
        else {
            commonPage.megaMenuAddressesLinks
                .filter(visible)
                .first()
                .click();
        }

        return this;
    }

    public CommonSteps clickOnOffersPageLink(Boolean isMobile) {
        if (isMobile) {
            commonPage.mobileMegaMenuOffersLinks
                .filter(visible)
                .first()
                .click();
        }
        else {
            commonPage.megaMenuOffersLinks
                .filter(visible)
                .first()
                .click();
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
