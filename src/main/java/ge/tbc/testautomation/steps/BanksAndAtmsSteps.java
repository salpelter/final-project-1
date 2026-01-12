package ge.tbc.testautomation.steps;

import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import ge.tbc.testautomation.pages.BanksAndAtmsPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BanksAndAtmsSteps {
    BanksAndAtmsPage banksAndAtmsPage = new BanksAndAtmsPage();

    public BanksAndAtmsSteps verifyLocationDotPresence() {
        banksAndAtmsPage.currentLocationDot.should(exist, Duration.ofSeconds(8));

        return this;
    }

    public BanksAndAtmsSteps scrollToMap() {
        executeJavaScript("arguments[0].scrollIntoView(true);", banksAndAtmsPage.map);

        return this;
    }

    // map marker indicates either a branch or an ATM
    public BanksAndAtmsSteps clickOnRandomVisibleMapMarker() {
        // there may be clustered marker that needs to be expanded, hence the loop, and
        // list on the left part of the screen may overlay a marker, hence the try-catch
        while (true) {
            var closestMapMarkers = banksAndAtmsPage.mapMarkers
                    .asFixedIterable()
                    .stream()
                    .filter(el -> isInViewport(el, banksAndAtmsPage.map))
                    .toList();

            var rand = new Random();
            var marker = closestMapMarkers.get(rand.nextInt(closestMapMarkers.size()));

            if (tryClick(marker)) {
                // all things that distinguish markers (like text or color) are contained in a
                // closed shadow-root, i couldn't access it to make sure that
                // actually the correct marker is highlighted
                if (getHighlightedBlocks().size() == 1) {
                    // this function is the only feasible way of verification that i found,
                    // unfortunately it makes it necessary to be call twice, which affects time
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
        var highlightedInfoBlocks = getHighlightedBlocks();

        Assert.assertEquals(highlightedInfoBlocks.size(), 1);

        return this;
    }

    // written this way just in case
    // there's multiple highlighted blocks
    // (there should be only one)
    private List<SelenideElement> getHighlightedBlocks() {
        var visibleBlocks = banksAndAtmsPage.markerInformationBlocks
                .asFixedIterable()
                .stream()
                .filter(el -> isInViewport(el, banksAndAtmsPage.listContainer))
                .toList();

        var highlightedBlocks = new ArrayList<SelenideElement>();

        visibleBlocks.forEach(marker -> {
            var classContent = marker.$x("./div").getAttribute("class");
            if (classContent.contains("active")) {
                highlightedBlocks.add(marker);
            }
        });

        return highlightedBlocks;
    }

    // other ways i tried of checking elements in the list on the left part of the screen
    // couldn't grab only visible elements so i had to go another way
    private boolean isInViewport(SelenideElement el, SelenideElement container) {
        return executeJavaScript(
                """
                const el = arguments[0];
                const container = arguments[1];
                const elRect = el.getBoundingClientRect();
                const cRect = container.getBoundingClientRect();
        
                return elRect.top >= cRect.top &&
                       elRect.bottom <= cRect.bottom;
                """,
                el, container
        );
    }
}
