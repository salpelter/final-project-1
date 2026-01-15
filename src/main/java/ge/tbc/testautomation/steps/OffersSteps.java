package ge.tbc.testautomation.steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ScrollIntoViewOptions;
import ge.tbc.testautomation.pages.OffersPage;

import java.time.Duration;
import java.util.Collection;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;

public class OffersSteps {
    OffersPage offersPage = new OffersPage();
    Random rand = new Random();

    public OffersSteps clickOnRandomOfferType() {
        offersPage.offerTypes.first().shouldBe(visible);
        var offerTypesCount = offersPage.offerTypes.size();

        var offerType = offersPage.offerTypes.get(rand.nextInt(offerTypesCount));
        offerType.click();

        return this;
    }

    public OffersSteps clickOnAllOffers() {
        offersPage.allOffersLink.shouldBe(visible, Duration.ofSeconds(6)).click();

        return this;
    }

    public OffersSteps pickRandomOffer() {
        offersPage.offerCards.first().shouldBe(visible, Duration.ofSeconds(8));
        var offersCount = offersPage.offerCards.size();

        var offerCard = offersPage.offerCards.get(rand.nextInt(offersCount));
        offerCard.scrollIntoView(ScrollIntoViewOptions.instant().block(ScrollIntoViewOptions.Block.center));
        offerCard.click();

        return this;
    }
}
