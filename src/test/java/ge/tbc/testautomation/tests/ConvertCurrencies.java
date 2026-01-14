package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.ExchangeRatesSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = {"Convert currencies (SCRUM-T27)"})
public class ConvertCurrencies extends BaseTest {
    @BeforeClass
    public void initializeSteps() {
        exchangeRatesSteps = new ExchangeRatesSteps();
    }

    @Parameters("isMobile")
    @Test(priority = 1)
    public void goToExchangeRatesPage(boolean isMobile) {
        commonSteps
                .openMenu(isMobile)
                .clickOnExchangeRatesPageLink(isMobile);
    }

    @Test(priority = 2, dependsOnMethods = "goToExchangeRatesPage")
    public void selectCurrencies() {
        exchangeRatesSteps
                .selectRandomFirstCurrency()
                .selectRandomSecondCurrency();
    }

    @Test(priority = 3, dependsOnMethods = "selectCurrencies")
    public void enterCurrencyAmount() {
        exchangeRatesSteps
                .enterRandomValueInFirstField()
                .verifyExchangeRate();
    }
}
