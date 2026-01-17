package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.MoneyTransfersSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MoneyTransferFeesTest extends BaseTest {
    @BeforeClass
    public void initializeSteps() {
        moneyTransfersSteps = new MoneyTransfersSteps();
    }

    @Parameters("isMobile")
    @Test(priority = 1)
    public void goToMoneyTransfersPage(boolean isMobile) {
        commonSteps
                .openMenu(isMobile);

        if (isMobile) {
            commonSteps
                    .unfoldOtherProductsSection();
        }

        commonSteps.clickOnMoneyTransfersPageLink();
    }

    @Test(priority = 2, dependsOnMethods = "goToMoneyTransfersPage")
    public void goToMoneyTransferFeesCalculator() {
        moneyTransfersSteps
                .scrollToMoneyTransferFeesCalculator();
    }

    @Test(priority = 3, dependsOnMethods = "goToMoneyTransferFeesCalculator")
    public void pickCurrency() {
        moneyTransfersSteps
                .clickOnRandomCurrency();
    }

    @Test(priority = 4, dependsOnMethods = "pickCurrency")
    public void enterSum() {
        moneyTransfersSteps
                .enterRandomTransferSum();
    }

    @Test(priority = 5, dependsOnMethods = "enterSum")
    public void pickCountry() {
        moneyTransfersSteps
                .clickOnRandomCountry()
                .verifyResultsVisible();
    }

}
