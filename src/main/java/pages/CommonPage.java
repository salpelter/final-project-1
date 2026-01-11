package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CommonPage {
    public SelenideElement denyCookiesButton = $x("//app-cookie-consent//button[text()=' უარყოფა ']");
}
