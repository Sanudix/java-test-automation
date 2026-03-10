package io.github.sanudix.automation.ui;

import io.github.sanudix.automation.base.BaseWebTest;
import io.github.sanudix.automation.ui.pages.BrokenLinksImagesPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Elements")
@Feature("Broken Links - Images")
@DisplayName("Elements → Broken Links - Images")
public class BrokenLinksImagesTest extends BaseWebTest {

    @Test
    @Story("Валидное изображение")
    @Severity(SeverityLevel.NORMAL)
    @Tag("smoke")
    @DisplayName("Валидное изображение отображается на странице")
    void validImageIsVisible() {
        openPage("broken");
        new BrokenLinksImagesPage()
                .checkValidImageVisible();
    }

    @Test
    @Story("Битое изображение")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Битое изображение присутствует в DOM")
    void brokenImageIsPresent() {
        openPage("broken");
        new BrokenLinksImagesPage()
                .checkBrokenImageExists();
    }

    @Test
    @Story("Валидная ссылка")
    @Severity(SeverityLevel.NORMAL)
    @Tag("smoke")
    @DisplayName("Валидная ссылка имеет корректный href")
    void validLinkHasCorrectHref() {
        openPage("broken");
        new BrokenLinksImagesPage()
                .checkValidLink();
    }

    @Test
    @Story("Битая ссылка")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Битая ссылка ведёт на страницу с ошибкой 500")
    void brokenLinkHasCorrectHref() {
        openPage("broken");
        new BrokenLinksImagesPage()
                .checkBrokenLink();
    }

    @Test
    @Story("Все элементы видимы")
    @Severity(SeverityLevel.MINOR)
    @Tag("regression")
    @DisplayName("Все элементы отображаются на странице")
    void allElementsAreVisible() {
        openPage("broken");
        new BrokenLinksImagesPage()
                .checkAllElementsPresent();
    }
}
