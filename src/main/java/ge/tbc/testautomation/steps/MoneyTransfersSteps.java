package ge.tbc.testautomation.steps;

import com.codeborne.selenide.ScrollIntoViewOptions;
import ge.tbc.testautomation.constants.Constants;
import ge.tbc.testautomation.pages.MoneyTransfersPage;

import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;

public class MoneyTransfersSteps {
    MoneyTransfersPage moneyTransfersPage = new MoneyTransfersPage();
    Random rand = new Random();

    public MoneyTransfersSteps scrollToMoneyTransferFeesCalculator() {
        moneyTransfersPage.moneyTransferFeesTab
                .shouldBe(visible, Duration.ofSeconds(15))
                .scrollIntoView(ScrollIntoViewOptions.instant().block(ScrollIntoViewOptions.Block.center))
                .click();

        return this;
    }

    public MoneyTransfersSteps clickOnRandomCurrency() {
        moneyTransfersPage.currencyDropdown.click();

        moneyTransfersPage.dropdownOptions.first().shouldBe(visible);
        var currencyCount = moneyTransfersPage.dropdownOptions.size();

        moneyTransfersPage.dropdownOptions.first().shouldBe(visible);
        moneyTransfersPage.dropdownOptions
                .get(rand.nextInt(currencyCount))
                .scrollIntoView(ScrollIntoViewOptions.instant().block(ScrollIntoViewOptions.Block.center))
                .click();

        return this;
    }

    public MoneyTransfersSteps enterRandomTransferSum() {

        var randomTransferSum = rand.nextDouble(Constants.CURRENCY_UPPER_BOUND);

        if (randomTransferSum < 0) {
            randomTransferSum = -randomTransferSum;
        }

        var formattedTransferSum = String.format("%f", randomTransferSum);

        moneyTransfersPage.transferSumInput.clear();
        moneyTransfersPage.transferSumInput.setValue(formattedTransferSum);

        return this;
    }

    public MoneyTransfersSteps clickOnRandomCountry() {
        moneyTransfersPage.countryDropdown.click();

        moneyTransfersPage.dropdownOptions.first().shouldBe(visible, Duration.ofSeconds(12));
        var countryCount = moneyTransfersPage.dropdownOptions.size();

        moneyTransfersPage.dropdownOptions.first().shouldBe(visible);
        moneyTransfersPage.dropdownOptions
                .get(rand.nextInt(countryCount))
                .scrollIntoView(ScrollIntoViewOptions.instant().block(ScrollIntoViewOptions.Block.center))
                .click();

        return this;
    }

    public MoneyTransfersSteps verifyResultsVisible() {
        if (moneyTransfersPage.transferFeeResults.isEmpty()) {
            System.out.println("No transfer options for the given sum, currency and/or country.");
        }
        else {
            // results load simultaneously, therefore checking
            // the first element should be enough
            moneyTransfersPage.transferFeeResults.first().shouldBe(visible);
        }

        return this;
    }
}
