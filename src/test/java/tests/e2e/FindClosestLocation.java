package tests.e2e;

import org.testng.annotations.Test;

@Test(groups = {"[Find the closest branch/ATM - SCRUM-T26]"})
public class FindClosestLocation extends BaseTest {
    @Test(priority = 1)
    public void goToBanksAndAtmsPage() {
        homePageSteps
                .verifyBanksAndAtmsCardVisibility()
                .scrollToBanksAndAtmsCard()
                .clickOnBanksAndAtmsCard();
    }

    // takes a while, but works
    @Test(priority = 2, dependsOnMethods = "goToBanksAndAtmsPage")
    public void findClosestLocation() {
        banksAndAtmsSteps
                .scrollToMap()
                .verifyLocationDotPresence()
                .clickOnRandomVisibleMapMarker();
    }

    @Test(priority = 3, dependsOnMethods = "findClosestLocation")
    public void verifyLocationHighlighted() {
        banksAndAtmsSteps
                .verifyMarkerInfoHighlighted();
    }
}
