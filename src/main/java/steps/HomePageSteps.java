package steps;

import pages.HomePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class HomePageSteps {
    HomePage homePage = new HomePage();

    public HomePageSteps verifyBanksAndAtmsCardVisibility() {
        homePage.banksAndAtmsCardLink.shouldBe(visible, Duration.ofSeconds(8));

        return this;
    }

    public HomePageSteps scrollToBanksAndAtmsCard() {
        executeJavaScript("arguments[0].scrollIntoView(true);", homePage.banksAndAtmsCardLink);

        return this;
    }

    public HomePageSteps clickOnBanksAndAtmsCard() {
        homePage.banksAndAtmsCardLink.click();

        return this;
    }
}
