package io.github.sanudix.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    private final SelenideElement elements             = $("a[href='/elements']");

    public MainPage() {
        open("/");
    }

    @Step("Открыть раздел Elements")
    public ElementsPage openElements() {
        elements.click();
        return new ElementsPage();
    }
}
