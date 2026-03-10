package io.github.sanudix.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ElementsPage {

    private final SelenideElement buttons            = $(byText("Buttons"));

    @Step("Открыть Buttons")
    public ButtonsPage openButtons() {
        buttons.click();
        return new ButtonsPage();
    }
}
