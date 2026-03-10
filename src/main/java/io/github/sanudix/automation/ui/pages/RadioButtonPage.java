package io.github.sanudix.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RadioButtonPage {

    private final SelenideElement yesLabel        = $("label[for='yesRadio']");
    private final SelenideElement impressiveLabel = $("label[for='impressiveRadio']");
    private final SelenideElement noInput         = $("#noRadio");

    private final SelenideElement resultText = $(".mt-3 span.text-success");

    @Step("Выбрать Yes")
    public RadioButtonPage clickYes() {
        yesLabel.click();
        return this;
    }

    @Step("Выбрать Impressive")
    public RadioButtonPage clickImpressive() {
        impressiveLabel.click();
        return this;
    }

    @Step("Проверить: результат — '{expectedText}'")
    public RadioButtonPage checkResultText(String expectedText) {
        resultText.shouldHave(text(expectedText));
        return this;
    }

    @Step("Проверить: кнопка No задизейблена")
    public RadioButtonPage checkNoButtonDisabled() {
        noInput.shouldHave(attribute("disabled"));
        return this;
    }
}
