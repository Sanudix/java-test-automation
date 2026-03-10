package io.github.sanudix.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ButtonsPage {

    private final SelenideElement doubleClickBtn = $("#doubleClickBtn");
    private final SelenideElement rightClickBtn  = $("#rightClickBtn");
    private final SelenideElement clickBtn       = $("button.btn-primary:not(#doubleClickBtn):not(#rightClickBtn)");

    private final SelenideElement doubleClickMsg = $("#doubleClickMessage");
    private final SelenideElement rightClickMsg  = $("#rightClickMessage");
    private final SelenideElement clickMsg       = $("#dynamicClickMessage");

    @Step("Двойной клик по кнопке")
    public ButtonsPage doubleClick() {
        doubleClickBtn.doubleClick();
        return this;
    }

    @Step("Правый клик по кнопке")
    public ButtonsPage rightClick() {
        rightClickBtn.contextClick();
        return this;
    }

    @Step("Клик по кнопке")
    public ButtonsPage click() {
        clickBtn.click();
        return this;
    }

    @Step("Проверить: сообщение о двойном клике отображается")
    public ButtonsPage checkDoubleClickMessage() {
        doubleClickMsg
                .shouldBe(visible)
                .shouldHave(text("You have done a double click"));
        return this;
    }

    @Step("Проверить: сообщение о правом клике отображается")
    public ButtonsPage checkRightClickMessage() {
        rightClickMsg
                .shouldBe(visible)
                .shouldHave(text("You have done a right click"));
        return this;
    }

    @Step("Проверить: сообщение о клике отображается")
    public ButtonsPage checkClickMessage() {
        clickMsg
                .shouldBe(visible)
                .shouldHave(text("You have done a dynamic click"));
        return this;
    }

    @Step("Проверить: сообщения не отображаются до взаимодействия")
    public ButtonsPage checkNoMessagesVisible() {
        doubleClickMsg.shouldNotBe(visible);
        rightClickMsg.shouldNotBe(visible);
        clickMsg.shouldNotBe(visible);
        return this;
    }
}
