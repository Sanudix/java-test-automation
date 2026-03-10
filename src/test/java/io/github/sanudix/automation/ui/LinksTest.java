package io.github.sanudix.automation.ui;

import io.github.sanudix.automation.base.BaseWebTest;
import io.github.sanudix.automation.ui.pages.LinksPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Epic("Elements")
@Feature("Links")
@DisplayName("Elements → Links")
public class LinksTest extends BaseWebTest {

    // --- Ссылки, открывающие новую вкладку ---

    @Test
    @Story("Simple Link")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Простая ссылка Home имеет корректный href")
    void simpleLinkHasCorrectHref() {
        openPage("links");
        new LinksPage()
                .checkSimpleLinkHref();
    }

    @Test
    @Story("Dynamic Link")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Динамическая ссылка Home имеет корректный href")
    void dynamicLinkHasCorrectHref() {
        openPage("links");
        new LinksPage()
                .checkDynamicLinkHref();
    }

    // --- API-ссылки: 7 тестов → 1 параметризованный ---

    @ParameterizedTest(name = "API-ссылка {0} → {1} {2}")
    @CsvSource({
            "created,      201, Created",
            "no-content,   204, No Content",
            "moved,        301, Moved Permanently",
            "bad-request,  400, Bad Request",
            "unauthorized, 401, Unauthorized",
            "forbidden,    403, Forbidden",
            "invalid-url,  404, Not Found"
    })
    @Story("API-ссылки")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    void apiLinkReturnsExpectedStatus(String linkId, int code, String statusText) {
        openPage("links");
        new LinksPage()
                .clickApiLink(linkId)
                .checkResponse(code, statusText);
    }

    // --- UI State ---

    @Test
    @Story("Состояние до взаимодействия")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Ответ не отображается до клика по API-ссылке")
    void noResponseBeforeClick() {
        openPage("links");
        new LinksPage()
                .checkResponseNotVisible();
    }
}
