package ge.tbc.testautomation.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementNotFound;
import ge.tbc.testautomation.constants.Urls;
import ge.tbc.testautomation.steps.ExchangeRatesSteps;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import ge.tbc.testautomation.steps.BanksAndAtmsSteps;
import ge.tbc.testautomation.steps.CommonSteps;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    // i decided to avoid initializing steps in BaseTest in order to
    // only initialize what's necessary in each class
    // p.s. let me know if this approach makes sense
    CommonSteps commonSteps = new CommonSteps();
    BanksAndAtmsSteps banksAndAtmsSteps;
    ExchangeRatesSteps exchangeRatesSteps;

    @Parameters("isMobile")
    @BeforeTest
    public void configureOptions(boolean isMobile) {
        var options = new ChromeOptions();
        var prefs = new HashMap<>();

        // location request
        prefs.put("profile.default_content_setting_values.geolocation", 1);
        options.setExperimentalOption("prefs", prefs);

        // screen size
        if (isMobile) {
            options.addArguments("--window-size=390,844");
            options.setExperimentalOption(
                    "mobileEmulation",
                    java.util.Map.of(
                            "deviceMetrics", java.util.Map.of(
                                    "width", 430,
                                    "height", 932,
                                    "pixelRatio", 3
                            ),
                            "userAgent",
                            "Mozilla/5.0 (iPhone; CPU iPhone OS 18_6 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/26.0 Mobile/15E148 Safari/604.1"
                    )

            );
        }

        Configuration.browserCapabilities = options;
    }

    @Parameters("isMobile")
    @BeforeClass
    public void setup(boolean isMobile) {
        open(Urls.HOME_PAGE_URL);

        if (!isMobile)
        {
            getWebDriver().manage().window().maximize();
        }

        try {
            // after closing the cookie prompt once
            // this may produce an exception next time
            // hence the try catch
            commonSteps
                    .verifyDenyCookiesButtonVisibility()
                    .clickOnDenyCookiesButton();
        }
        catch(ElementNotFound e) {}
    }
}
