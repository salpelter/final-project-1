package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MoneyTransfersPage {
    public SelenideElement moneyTransferFeesTab = $x("//span[text()='გზავნილის გაგზავნის საკომისიო']");
    public SelenideElement currencyDropdown = $x("//input[@id='tbcx-text-input-1']//parent::div//following-sibling::div//i");
    public SelenideElement countryDropdown = $x("//div[text()=' აირჩიე ქვეყანა ']//ancestor::tbcx-dropdown-selector//i");
    public ElementsCollection dropdownOptions = $$("div tbcx-dropdown-popover-item");
    public SelenideElement transferSumInput = $("#tbcx-text-input-1");
    public ElementsCollection transferFeeResults = $$x("//tbcx-pw-money-transfer-fee-calculator//tbcx-pw-money-transfer-system-card");
}
