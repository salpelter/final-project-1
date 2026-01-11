package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    public SelenideElement banksAndAtmsCardLink = $x("//div[@class='carousel']//a[@href='/ka/atms&branches']");
}
