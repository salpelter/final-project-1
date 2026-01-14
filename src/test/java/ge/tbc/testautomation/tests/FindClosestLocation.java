package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.BanksAndAtmsSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = {"Find the closest branch/ATM (SCRUM-T26)"})
public class FindClosestLocation extends BaseTest {
    @BeforeClass
    public void initializeSteps() {
        banksAndAtmsSteps = new BanksAndAtmsSteps();
    }

    @Parameters("isMobile")
    @Test(priority = 1)
    public void goToBanksAndAtmsPage(boolean isMobile) {
        if (isMobile) {
            commonSteps
                    .clickOnHamburgerMenu()
                    .clickOnAddressesMenuItem(isMobile);
        }
        else {
            commonSteps
                    .hoverOverPersonalMegaMenu()
                    .clickOnAddressesMenuItem(isMobile);
        }
    }

    // verifications can potentially take a few minutes, but it works
    @Test(priority = 2)
    public void findClosestLocation() {
        banksAndAtmsSteps
                .scrollToMap()
                .verifyLocationDotPresence()
                .clickOnRandomVisibleMapMarker();
    }

    @Test(priority = 3)
    public void verifyLocationHighlighted() {
        banksAndAtmsSteps
                .verifyMarkerInfoHighlighted();
    }
}
