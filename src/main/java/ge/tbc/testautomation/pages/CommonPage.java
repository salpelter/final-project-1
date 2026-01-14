package ge.tbc.testautomation.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CommonPage {
    public SelenideElement denyCookiesButton = $x("//app-cookie-consent//button[text()=' უარყოფა ']");
    public SelenideElement mobileHamburgerMenuIcon = $("tbcx-pw-hamburger-menu tbcx-icon");
    public SelenideElement personalMegaMenu = $x("//tbcx-pw-navigation//div[text()=' ჩემთვის ']");

    // couldn't find a more dynamic way to grab these menu items
    public SelenideElement megaMenuExchangeRatesLink = $x("(//span[text()=' ვალუტის კურსები'])[1]");
    public SelenideElement mobileMegaMenuExchangeRatesLink = $x("(//span[text()=' ვალუტის კურსები'])[3]");
    public SelenideElement megaMenuAddressesLink = $x("(//span[text()=' მისამართები'])[1]");
    public SelenideElement mobileMegaMenuAddressesLink = $x("(//span[text()=' მისამართები'])[3]");
    public SelenideElement megaMenuOffersLink = $x("(//span[text()=' შეთავაზებები'])[1]");
    public SelenideElement mobileMegaMenuOffersLink = $x("(//span[text()=' შეთავაზებები'])[2]");
}
