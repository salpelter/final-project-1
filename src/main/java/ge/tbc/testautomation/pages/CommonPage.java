package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CommonPage {
    public SelenideElement denyCookiesButton = $x("//app-cookie-consent//button[text()=' უარყოფა ']");
    public SelenideElement mobileHamburgerMenuIcon = $("tbcx-pw-hamburger-menu tbcx-icon");
    public SelenideElement personalMegaMenu = $x("//tbcx-pw-navigation//div[text()=' ჩემთვის ']");

    // at any times there's usually a few of these links, but only one of them is visible
    public ElementsCollection megaMenuExchangeRatesLinks = $$x("//span[text()=' ვალუტის კურსები']");
    public ElementsCollection mobileMegaMenuExchangeRatesLinks = $$x("//span[text()=' ვალუტის კურსები']");
    public ElementsCollection megaMenuAddressesLinks = $$x("//span[text()=' მისამართები']");
    public ElementsCollection mobileMegaMenuAddressesLinks = $$x("//span[text()=' მისამართები']");
    public ElementsCollection megaMenuOffersLinks = $$x("//span[text()=' შეთავაზებები']");
    public ElementsCollection mobileMegaMenuOffersLinks = $$x("//span[text()=' შეთავაზებები']");
    public ElementsCollection megaMenuMoneyTransfersLinks = $$x("//span[text()='ფულადი გზავნილები']");
    public ElementsCollection mobileMegaMenuOtherProductsSections = $$x("//span[text()='სხვა პროდუქტები']");
}
