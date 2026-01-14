package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.BranchesAndAtmsSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = {"Find a nearby branch/ATM (SCRUM-T26)"})
public class FindNearbyLocation extends BaseTest {
    @BeforeClass
    public void initializeSteps() {
        branchesAndAtmsSteps = new BranchesAndAtmsSteps();
    }

    @Parameters("isMobile")
    @Test(priority = 1)
    public void goToBranchesAndAtmsPage(boolean isMobile) {
        commonSteps
                .openMenu(isMobile)
                .clickOnAddressesPageLink(isMobile);
    }

    @Parameters("isMobile")
    @Test(priority = 2, dependsOnMethods = "goToBranchesAndAtmsPage")
    public void findNearbyLocation(boolean isMobile) {
        branchesAndAtmsSteps
                .scrollToMap(isMobile)
                .verifyLocationDotPresence()
                .clickOnRandomVisibleMapMarker();
    }

    @Test(priority = 3, dependsOnMethods = "findNearbyLocation")
    public void verifyLocationHighlighted() {
        branchesAndAtmsSteps
                .verifyMarkerInfoHighlighted();
    }
}
