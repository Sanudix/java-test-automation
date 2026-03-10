package io.github.sanudix.automation.ui;

import io.github.sanudix.automation.base.BaseWebTest;
import io.github.sanudix.automation.ui.pages.WebTablesPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Elements")
@Feature("Web Tables")
@DisplayName("Elements → Web Tables")
public class WebTablesTest extends BaseWebTest {

    @Test
    @Story("Добавление записи")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Добавление новой записи")
    void addNewRecord() {
        openPage("webtables");
        new WebTablesPage()
                .openAddForm()
                .fillForm("John", "Doe", "john.doe@example.com", "30", "5000", "QA")
                .submitForm()
                .checkRowExists("john.doe@example.com")
                .checkRowContains("john.doe@example.com", "John", "Doe", "5000", "QA");
    }

    @Test
    @Story("Поиск по email")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Поиск по существующему email")
    void searchExistingByEmail() {
        openPage("webtables");
        new WebTablesPage()
                .search("cierra@example.com")
                .checkRowExists("cierra@example.com")
                .checkRowContains("cierra@example.com", "Cierra");
    }

    @Test
    @Story("Поиск несуществующего значения")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Поиск по несуществующему значению")
    void searchNonExistingValue() {
        openPage("webtables");
        new WebTablesPage()
                .search("non-existing-value")
                .checkNoRowsFound();
    }

    @Test
    @Story("Дефолтные записи")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Отображение дефолтных записей")
    void defaultRowsVisible() {
        openPage("webtables");
        new WebTablesPage()
                .checkTotalDataRows(3)
                .checkRowExists("Cierra")
                .checkRowExists("Alden")
                .checkRowExists("Kierra");
    }
}
