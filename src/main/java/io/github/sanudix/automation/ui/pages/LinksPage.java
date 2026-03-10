package io.github.sanudix.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LinksPage {

    private final SelenideElement simpleLink  = $("#simpleLink");
    private final SelenideElement dynamicLink = $("#dynamicLink");

    private final SelenideElement linkResponse = $("#linkResponse");

    @Step("Клик по API-ссылке '{linkId}'")
    public LinksPage clickApiLink(String linkId) {
        $("#" + linkId).click();
        return this;
    }

    @Step("Проверить: Simple Link имеет корректный href")
    public LinksPage checkSimpleLinkHref() {
        simpleLink
                .shouldBe(visible)
                .shouldHave(attribute("href", "https://demoqa.com/"))
                .shouldHave(attribute("target", "_blank"));
        return this;
    }

    @Step("Проверить: Dynamic Link имеет корректный href")
    public LinksPage checkDynamicLinkHref() {
        dynamicLink
                .shouldBe(visible)
                .shouldHave(attribute("href", "https://demoqa.com/"))
                .shouldHave(attribute("target", "_blank"));
        return this;
    }

    @Step("Проверить: ответ содержит код {code} и текст '{statusText}'")
    public LinksPage checkResponse(int code, String statusText) {
        linkResponse
                .shouldBe(visible)
                .shouldHave(text(String.valueOf(code)))
                .shouldHave(text(statusText));
        return this;
    }

    @Step("Проверить: ответ не отображается до клика")
    public LinksPage checkResponseNotVisible() {
        linkResponse.shouldNotBe(visible);
        return this;
    }
}
