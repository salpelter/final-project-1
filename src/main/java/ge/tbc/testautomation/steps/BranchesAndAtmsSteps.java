package ge.tbc.testautomation.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ScrollIntoViewOptions;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import ge.tbc.testautomation.pages.BranchesAndAtmsPage;
import org.testng.SkipException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BranchesAndAtmsSteps {
    BranchesAndAtmsPage branchesAndAtmsPage = new BranchesAndAtmsPage();
    Random rand = new Random();

    public BranchesAndAtmsSteps verifyLocationDotPresence() {
        branchesAndAtmsPage.currentLocationDot.should(exist, Duration.ofSeconds(10));

        return this;
    }

    public BranchesAndAtmsSteps scrollToMap(boolean isMobile) {
        branchesAndAtmsPage.map.shouldBe(visible, Duration.ofSeconds(8));

        if (isMobile) {
            branchesAndAtmsPage.map.scrollIntoView(
                    ScrollIntoViewOptions.instant().block(ScrollIntoViewOptions.Block.center));
        }
        else {
            branchesAndAtmsPage.map.scrollIntoView(
                    ScrollIntoViewOptions.instant().block(ScrollIntoViewOptions.Block.end));
        }

        return this;
    }

    // map marker indicates either a branch or an ATM
    public BranchesAndAtmsSteps clickOnRandomVisibleMapMarker() {
        // there may be clustered marker that needs to be expanded, hence the loop,
        // and some other elements may overlay a marker, hence the try-catch
        while (true) {
            List<SelenideElement> nearbyMapMarkers;

            try {
                nearbyMapMarkers = getElementsInViewport(branchesAndAtmsPage.mapMarkers, branchesAndAtmsPage.map);
            }
            catch (NoSuchElementException e) {
                nearbyMapMarkers = List.of();
            }

            if (nearbyMapMarkers.isEmpty()) {
                throw new IllegalStateException("No map markers found. Try a different location.");
            }

            var marker = nearbyMapMarkers.get(rand.nextInt(nearbyMapMarkers.size()));

            if (tryClick(marker)) {
                // all things that distinguish markers (like text or color) are contained in a
                // closed shadow-root, i couldn't access it to make sure that
                // actually the correct marker is highlighted
                if (branchesAndAtmsPage.highlightedMarkerInformationBlock.exists()) {
                    break;
                }
            }
        }

        return this;
    }

    public BranchesAndAtmsSteps verifyMarkerInfoHighlighted() {
        Assert.assertTrue(branchesAndAtmsPage.highlightedMarkerInformationBlock.exists());

        return this;
    }

    public BranchesAndAtmsSteps clickOnCityDropdown() {
        branchesAndAtmsPage.cityDropdown.click();

        return this;
    }

    public BranchesAndAtmsSteps clickOnRandomCity() {
        branchesAndAtmsPage.cities.first().shouldBe(visible);
        int citiesCount = branchesAndAtmsPage.cities.size();

        var randomCity = branchesAndAtmsPage.cities.get(rand.nextInt(citiesCount));
        randomCity.scrollIntoView(true);
        randomCity.click();

        return this;
    }

    public BranchesAndAtmsSteps clickOnBranchesFilter() {
        branchesAndAtmsPage.branchesFilter.shouldBe(visible).click();

        return this;
    }

    public BranchesAndAtmsSteps clickOnOpenFilter() {
        branchesAndAtmsPage.openFilter.shouldBe(visible).click();

        return this;
    }

    private boolean tryClick(SelenideElement element) {
        try {
            executeJavaScript("arguments[0].click();", element);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    // couldn't find a better way for checking elements
    private List<SelenideElement> getElementsInViewport(ElementsCollection elements, SelenideElement container) {
        List<SelenideElement> result = new ArrayList<>();

        List<SelenideElement> snapshot = elements.snapshot().asDynamicIterable().stream().toList();

        for (SelenideElement el : snapshot) {
            try {
                Boolean inView = executeJavaScript(
                        """
                        const el = arguments[0];
                        const container = arguments[1];
                        const cRect = container.getBoundingClientRect();
                        const elRect = el.getBoundingClientRect();
                        return elRect.top >= cRect.top && elRect.bottom <= cRect.bottom;
                        """,
                        el.toWebElement(),
                        container.toWebElement()
                );
                if (Boolean.TRUE.equals(inView)) {
                    result.add(el);
                }
            } catch (StaleElementReferenceException | IndexOutOfBoundsException ignored) {
                // skip stale or removed elements
            }
        }

        return result;
    }
}
