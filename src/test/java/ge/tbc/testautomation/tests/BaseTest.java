package ge.tbc.testautomation.tests;

import com.codeborne.selenide.Configuration;
import ge.tbc.testautomation.constants.Urls;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import ge.tbc.testautomation.steps.BanksAndAtmsSteps;
import ge.tbc.testautomation.steps.CommonPageSteps;
import ge.tbc.testautomation.steps.HomePageSteps;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    CommonPageSteps commonPageSteps = new CommonPageSteps();
    HomePageSteps homePageSteps = new HomePageSteps();
    BanksAndAtmsSteps banksAndAtmsSteps = new BanksAndAtmsSteps();

    @BeforeSuite
    public void configureLocationRequest() {
        var options = new ChromeOptions();
        var prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 1);
        options.setExperimentalOption("prefs", prefs);
        Configuration.browserCapabilities = options;
    }

    @BeforeClass
    public void setUp() {
        open(Urls.HOME_PAGE_URL);
        getWebDriver().manage().window().maximize();

        commonPageSteps
                .verifyDenyCookiesButtonVisibility()
                .clickOnDenyCookiesButton();
    }
}
