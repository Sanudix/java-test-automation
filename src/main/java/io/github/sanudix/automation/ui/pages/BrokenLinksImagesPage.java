package io.github.sanudix.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class BrokenLinksImagesPage {

    private final SelenideElement validImage  = $("img[src='/images/Toolsqa.jpg']");
    private final SelenideElement brokenImage = $("img[src='/images/Toolsqa_1.jpg']");

    private final SelenideElement validLink  = $("a[href='http://demoqa.com']");
    private final SelenideElement brokenLink = $("a[href='http://the-internet.herokuapp.com/status_codes/500']");


    @Step("Проверить: валидное изображение отображается")
    public BrokenLinksImagesPage checkValidImageVisible() {
        validImage
                .shouldBe(visible)
                .shouldHave(attribute("src", "https://demoqa.com/images/Toolsqa.jpg"));
        return this;
    }

    @Step("Проверить: битое изображение присутствует в DOM")
    public BrokenLinksImagesPage checkBrokenImageExists() {
        brokenImage
                .shouldBe(exist)
                .shouldHave(attribute("src", "https://demoqa.com/images/Toolsqa_1.jpg"));
        return this;
    }

    @Step("Проверить: валидная ссылка имеет корректный href")
    public BrokenLinksImagesPage checkValidLink() {
        validLink
                .shouldBe(visible)
                .shouldHave(text("Click Here for Valid Link"))
                .shouldHave(attribute("href", "http://demoqa.com/"));
        return this;
    }

    @Step("Проверить: битая ссылка ведёт на страницу с ошибкой 500")
    public BrokenLinksImagesPage checkBrokenLink() {
        brokenLink
                .shouldBe(visible)
                .shouldHave(text("Click Here for Broken Link"))
                .shouldHave(attribute("href", "http://the-internet.herokuapp.com/status_codes/500"));
        return this;
    }

    @Step("Проверить: все элементы отображаются на странице")
    public BrokenLinksImagesPage checkAllElementsPresent() {
        validImage.shouldBe(visible);
        brokenImage.shouldBe(exist);
        validLink.shouldBe(visible);
        brokenLink.shouldBe(visible);
        return this;
    }
}
