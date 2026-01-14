package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BanksAndAtmsPage {
    public SelenideElement currentLocationDot = $("google-map svg > circle.solid-dot");
    public ElementsCollection mapMarkers = $$x("//gmp-advanced-marker[contains(@slot, 'internal-visible-gmp-advanced-markers')]");
    public SelenideElement highlightedMarkerInformationBlock = $x("//app-atm-branches-section-list-item/div[contains(@class, 'active')]");
    public SelenideElement map = $("google-map div.map-container");
    public SelenideElement listContainer = $x("//div[@class='tbcx-pw-atm-branches-section__list-wrapper']");
}
