package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BranchesAndAtmsPage {
    public SelenideElement currentLocationDot = $("google-map svg > circle.solid-dot");
    public ElementsCollection mapMarkers = $$x("//gmp-advanced-marker[contains(@slot, 'internal-visible-gmp-advanced-markers')]");
    public SelenideElement highlightedMarkerInformationBlock = $x("//app-atm-branches-section-list-item/div[contains(@class, 'active')]");
    public SelenideElement map = $x("//google-map/div[@class='map-container']/div");
    public SelenideElement cityDropdown = $("tbcx-dropdown-selector i");
    public ElementsCollection cities = $$x("//tbcx-dropdown-popover-item");
    public SelenideElement branchesFilter = $x("//span[text()='ფილიალები']");
    public SelenideElement openFilter = $x("//span[text()=' ღიაა ']");
}
