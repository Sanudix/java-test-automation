package io.github.sanudix.automation.ui;

import io.github.sanudix.automation.base.BaseWebTest;
import io.github.sanudix.automation.ui.pages.ButtonsPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Elements")
@Feature("Buttons")
@DisplayName("Elements → Buttons")
public class ButtonsTest extends BaseWebTest {

    @Test
    @Story("Двойной клик")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Двойной клик по кнопке")
    void doubleClickButton() {
        openPage("buttons");
        new ButtonsPage()
                .doubleClick()
                .checkDoubleClickMessage();
    }

    @Test
    @Story("Правый клик")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Правый клик по кнопке")
    void rightClickButton() {
        openPage("buttons");
        new ButtonsPage()
                .rightClick()
                .checkRightClickMessage();
    }

    @Test
    @Story("Обычный клик")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Обычный клик по кнопке")
    void dynamicClickButton() {
        openPage("buttons");
        new ButtonsPage()
                .click()
                .checkClickMessage();
    }

    @Test
    @Story("Состояние до взаимодействия")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Сообщения не отображаются до взаимодействия")
    void noMessagesBeforeInteraction() {
        openPage("buttons");
        new ButtonsPage()
                .checkNoMessagesVisible();
    }

    @Test
    @Story("E2E навигация")
    @Severity(SeverityLevel.NORMAL)
    @Tag("e2e")
    @DisplayName("E2E: навигация через меню → двойной клик")
    void e2eDoubleClick() {
        openMainPage()
                .openElements()
                .openButtons()
                .doubleClick()
                .checkDoubleClickMessage();
    }
}
