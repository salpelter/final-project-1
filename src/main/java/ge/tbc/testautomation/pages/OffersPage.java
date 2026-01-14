package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class OffersPage {
    public ElementsCollection offerTypes = $$x("//tbcx-pw-tab-menu/div/button");
    public ElementsCollection offerCards = $$("app-marketing-list div a");
    public SelenideElement allOffersLink = $x("//tbcx-pw-media/following-sibling::div//tbcx-pw-button//a[contains(@href, '/ka/offers/all-offers')]");
}
