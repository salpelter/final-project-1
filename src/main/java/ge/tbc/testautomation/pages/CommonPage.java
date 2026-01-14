package ge.tbc.testautomation.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CommonPage {
    public SelenideElement denyCookiesButton = $x("//app-cookie-consent//button[text()=' უარყოფა ']");
    public SelenideElement mobileHamburgerMenuIcon = $("tbcx-pw-hamburger-menu tbcx-icon");
    public SelenideElement personalMegaMenu = $x("//tbcx-pw-navigation//div[text()=' ჩემთვის ']");

    // couldn't find a more dynamic way to grab these menu items
    public SelenideElement exchangeRatesPageLink = $x("(//tbcx-pw-mega-menu-quick-acitons-item//span[text()=' ვალუტის კურსები'])[1]");
    public SelenideElement mobileExchangeRatesPageLink = $x("(//tbcx-pw-mega-menu-quick-acitons-item//span[text()=' ვალუტის კურსები'])[3]");
    public SelenideElement megaMenuAddressesItem = $x("(//a[@href='/ka/atms&branches']//span[text()=' მისამართები'])[1]");
    public SelenideElement mobileMegaMenuAddressesItem = $x("(//a[@href='/ka/atms&branches']//span[text()=' მისამართები'])[3]");
}
