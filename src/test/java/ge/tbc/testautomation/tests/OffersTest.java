package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.OffersSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = {"View offers (SCRUM-T29)"})
public class OffersTest extends BaseTest {
    @BeforeClass
    public void initializeSteps() {
        offersSteps = new OffersSteps();
    }

    @Parameters("isMobile")
    @Test(priority = 1)
    public void goToOffersPage(boolean isMobile) {
        commonSteps
                .openMenu(isMobile)
                .clickOnOffersPageLink(isMobile);

        offersSteps
                .clickOnAllOffers();
    }

    @Test(priority = 2, dependsOnMethods = "goToOffersPage")
    public void filterByOfferType() {
        offersSteps
                .clickOnRandomOfferType();
    }

    @Test(priority = 3, dependsOnMethods = "filterByOfferType")
    public void pickOffer() {
        offersSteps
                .pickRandomOffer();
    }
}
