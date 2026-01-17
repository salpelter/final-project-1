package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class ExchangeRatesPage {
    public ElementsCollection currencyDropdownButtons =
            $$("tbcx-dropdown-selector i");
    public ElementsCollection availableCurrencies =
            $$("div tbcx-dropdown-popover-item > div");
    public SelenideElement firstInputField =
            $x("(//tbcx-text-input//input)[1]");
    public SelenideElement secondInputField =
            $x("(//tbcx-text-input//input)[2]");
    public SelenideElement exchangeRateDescription =
            $x("//tbcx-input-with-selector//following-sibling::div[contains(@class, 'tbcx-pw-exchange-rates-calculator__description')]");
}
