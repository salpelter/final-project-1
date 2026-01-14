package ge.tbc.testautomation.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ge.tbc.testautomation.pages.BanksAndAtmsPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BanksAndAtmsSteps {
    BanksAndAtmsPage banksAndAtmsPage = new BanksAndAtmsPage();

    public BanksAndAtmsSteps verifyLocationDotPresence() {
        banksAndAtmsPage.currentLocationDot.should(exist, Duration.ofSeconds(8));

        return this;
    }

    public BanksAndAtmsSteps scrollToMap() {
        banksAndAtmsPage.map.shouldBe(visible);
        executeJavaScript("arguments[0].scrollIntoView(false);", banksAndAtmsPage.map.toWebElement());

        return this;
    }

    // map marker indicates either a branch or an ATM
    public BanksAndAtmsSteps clickOnRandomVisibleMapMarker() {
        // there may be clustered marker that needs to be expanded, hence the loop,
        // and some other elements may overlay a marker, hence the try-catch
        while (true) {
            var closestMapMarkers = getElementsInViewport(banksAndAtmsPage.mapMarkers, banksAndAtmsPage.map);

            var rand = new Random();
            var marker = closestMapMarkers.get(rand.nextInt(closestMapMarkers.size()));

            if (tryClick(marker)) {
                // all things that distinguish markers (like text or color) are contained in a
                // closed shadow-root, i couldn't access it to make sure that
                // actually the correct marker is highlighted
                if (banksAndAtmsPage.highlightedMarkerInformationBlock.exists()) {
                    break;
                }
            }
        }

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

    public BanksAndAtmsSteps verifyMarkerInfoHighlighted() {
        Assert.assertTrue(banksAndAtmsPage.highlightedMarkerInformationBlock.exists());

        return this;
    }

    // couldn't find a better way for checking elements
    private List<SelenideElement> getElementsInViewport(ElementsCollection elements, SelenideElement container) {
        List<WebElement> webElements = elements
                .stream()
                .map(SelenideElement::toWebElement)
                .toList();

        // executeJavascript only understands Selenium's WebElement and
        // numbers in js are always 64-bit floating-point, hence Long
        List<Long> indices = executeJavaScript(
                """
                const container = arguments[0];
                const elements = arguments[1];
                const cRect = container.getBoundingClientRect();
    
                return Array.from(elements)
                    .map((el, index) => {
                        const elRect = el.getBoundingClientRect();
                        return {
                            index: index,
                            inView: elRect.top >= cRect.top && elRect.bottom <= cRect.bottom
                        };
                    })
                    .filter(item => item.inView)
                    .map(item => item.index);
                """,
                container.toWebElement(), webElements
        );

        // map indices back to selenide elements
        List<SelenideElement> result = new ArrayList<>();
        for (var index : indices) {
            result.add(elements.get(index.intValue()));
        }

        return result;
    }
}
