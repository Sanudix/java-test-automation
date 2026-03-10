package io.github.sanudix.automation.ui;

import io.github.sanudix.automation.base.BaseWebTest;
import io.github.sanudix.automation.ui.pages.DynamicPropertiesPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Elements")
@Feature("Dynamic Properties")
@DisplayName("Elements → Dynamic Properties")
public class DynamicPropertiesTest extends BaseWebTest {

    @Test
    @Story("Кнопка неактивна при загрузке")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Кнопка неактивна при загрузке страницы")
    void buttonDisabledInitially() {
        openPage("dynamic-properties");
        new DynamicPropertiesPage()
                .checkEnableButtonDisabled();
    }

    @Test
    @Story("Кнопка активируется с задержкой")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Кнопка становится активной через 5 секунд")
    void buttonEnabledAfterDelay() {
        openPage("dynamic-properties");
        new DynamicPropertiesPage()
                .checkEnableButtonEnabled();
    }

    @Test
    @Story("Нет класса цвета при загрузке")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Кнопка изначально не имеет класса text-danger")
    void buttonNoColorClassInitially() {
        openPage("dynamic-properties");
        new DynamicPropertiesPage()
                .checkColorButtonNoClass();
    }

    @Test
    @Story("Цвет меняется с задержкой")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Кнопка получает класс text-danger через 5 секунд")
    void buttonChangesColorAfterDelay() {
        openPage("dynamic-properties");
        new DynamicPropertiesPage()
                .checkColorButtonHasClass();
    }

    @Test
    @Story("Кнопка скрыта при загрузке")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Кнопка не видна при загрузке страницы")
    void buttonNotVisibleInitially() {
        openPage("dynamic-properties");
        new DynamicPropertiesPage()
                .checkVisibleButtonHidden();
    }

    @Test
    @Story("Кнопка появляется с задержкой")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Кнопка появляется через 5 секунд")
    void buttonVisibleAfterDelay() {
        openPage("dynamic-properties");
        new DynamicPropertiesPage()
                .checkVisibleButtonAppears();
    }
}
