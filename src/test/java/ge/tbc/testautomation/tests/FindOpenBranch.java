package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.BranchesAndAtmsSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = {"Find an open branch in a city (SCRUM-T28)"})
public class FindOpenBranch extends BaseTest {
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

    @Test(priority = 2, dependsOnMethods = "goToBranchesAndAtmsPage")
    public void selectCity() {
        branchesAndAtmsSteps
                .clickOnCityDropdown()
                .clickOnRandomCity();
    }

    @Test(priority = 3, dependsOnMethods = "selectCity")
    public void filterByBranches() {
        branchesAndAtmsSteps
                .clickOnBranchesFilter();
    }

    @Parameters("isMobile")
    @Test(priority = 4, dependsOnMethods = "filterByBranches")
    public void filterByOpenBranches(boolean isMobile) {
        branchesAndAtmsSteps
                .scrollToMap(isMobile)
                .clickOnOpenFilter();
    }

    @Test(priority = 5, dependsOnMethods = "filterByOpenBranches")
    public void selectBranch() {
        branchesAndAtmsSteps
                .clickOnRandomVisibleMapMarker();
    }

    @Test(priority = 6, dependsOnMethods = "selectBranch")
    public void verifyLocationHighlighted() {
        branchesAndAtmsSteps
                .verifyMarkerInfoHighlighted();
    }
}
