package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.constants.Constants;
import ge.tbc.testautomation.pages.ExchangeRatesPage;
import org.testng.Assert;

import java.util.Random;

public class ExchangeRatesSteps {
    ExchangeRatesPage exchangeRatesPage = new ExchangeRatesPage();
    Random rand = new Random();

    public ExchangeRatesSteps selectRandomFirstCurrency() {
        var firstCurrencyDropdown = exchangeRatesPage.currencyDropdownButtons.get(0);

        firstCurrencyDropdown.click();
        var currenciesCount = exchangeRatesPage.availableCurrencies.size();

        exchangeRatesPage.availableCurrencies.get(rand.nextInt(currenciesCount)).click();

        return this;
    }

    public ExchangeRatesSteps selectRandomSecondCurrency() {
        var firstCurrencyDropdownButton = exchangeRatesPage.currencyDropdownButtons.get(0);
        var secondCurrencyDropdownButton = exchangeRatesPage.currencyDropdownButtons.get(1);

        firstCurrencyDropdownButton.click();
        var currenciesCount = exchangeRatesPage.availableCurrencies.size();

        // in a list just in case
        var currentCurrency = exchangeRatesPage.availableCurrencies.stream()
                .filter(currency -> currency
                        .getAttribute("class")
                        .contains("tbcx-dropdown-popover-item__selected"))
                .toList();

        Assert.assertEquals(currentCurrency.size(), 1);

        // getting it before it's gone
        var firstCurrencyText = currentCurrency.getFirst().text();

        // to hide it back
        firstCurrencyDropdownButton.click();

        while (true) {
            if (!exchangeRatesPage.availableCurrencies.first().isDisplayed()) {
                secondCurrencyDropdownButton.click();
            }

            var secondCurrency = exchangeRatesPage.availableCurrencies.get(rand.nextInt(currenciesCount));
            var secondCurrencyText = secondCurrency.text();

            if (!secondCurrencyText.equals(firstCurrencyText)) {
                secondCurrency.click();
                break;
            }
        }

        return this;
    }

    public ExchangeRatesSteps enterRandomValueInFirstField() {
        var firstValue = rand.nextDouble(Constants.CURRENCY_UPPER_BOUND);

        if (firstValue < 0) {
            firstValue = -firstValue;
        }

        var formattedFirstValue = String.format("%f", firstValue);

        exchangeRatesPage.firstInputField.clear();
        exchangeRatesPage.firstInputField.sendKeys(formattedFirstValue);

        return this;
    }

    // no way to get the exact rate so i'm only verifying first 4 digits here
    public ExchangeRatesSteps verifyExchangeRate() {
        var exchangeRateDescription = exchangeRatesPage.exchangeRateDescription.text();

        var expectedExchangeRateValue = Double.parseDouble(exchangeRateDescription.split("[^\\d.]+")[1]);
        var firstCurrencyValue = Double.parseDouble(exchangeRatesPage.firstInputField.getValue());
        var secondCurrencyValue = Double.parseDouble(exchangeRatesPage.secondInputField.getValue());

        double actualExchangeRate = secondCurrencyValue / firstCurrencyValue;

        String roundedActualExchangeRateValue = String.format("%.4f", actualExchangeRate);
        String roundedExchangeRateValue = String.format("%.4f", expectedExchangeRateValue);

        Assert.assertEquals(Double.parseDouble(roundedActualExchangeRateValue), Double.parseDouble(roundedExchangeRateValue));

        return this;
    }
}
