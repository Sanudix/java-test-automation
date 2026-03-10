package io.github.sanudix.automation.ui;

import io.github.sanudix.automation.base.BaseWebTest;
import io.github.sanudix.automation.ui.pages.TextBoxPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Elements")
@Feature("Text Box")
@DisplayName("Elements → Text Box")
public class TextBoxTest extends BaseWebTest {

    @Test
    @Story("Открытие страницы")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("smoke")
    @DisplayName("Успешное открытие страницы")
    void openTextBox() {
        openPage("text-box");
        new TextBoxPage()
                .checkOutputNotVisible();
    }

    @Test
    @Story("Ввод данных")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Успешный ввод данных")
    void insertTextBoxData() {
        openPage("text-box");
        new TextBoxPage()
                .insertData()
                .checkOutputName("Иван Петров");
    }

    @Test
    @Story("Невалидный email")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("regression")
    @DisplayName("Невалидный email — форма не принимает данные")
    void invalidEmail() {
        openPage("text-box");
        new TextBoxPage()
                .insertDataWithEmail("invalid-email")
                .checkOutputNotVisible();
    }

    @Test
    @Story("Пустая форма")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Пустая форма — вывод не появляется")
    void emptyForm() {
        openPage("text-box");
        new TextBoxPage()
                .submitEmpty()
                .checkOutputNotVisible();
    }

    @Test
    @Story("Отображение email")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Email корректно отображается в выводе")
    void outputContainsEmail() {
        openPage("text-box");
        new TextBoxPage()
                .insertData()
                .checkOutputEmail("ivan@example.com");
    }

    @Test
    @Story("Отображение Current Address")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Current Address корректно отображается в выводе")
    void outputContainsCurrentAddress() {
        openPage("text-box");
        new TextBoxPage()
                .insertData()
                .checkOutputCurrentAddress("Москва, ул. Тестовая, д. 1");
    }

    @Test
    @Story("Отображение Permanent Address")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Permanent Address корректно отображается в выводе")
    void outputContainsPermanentAddress() {
        openPage("text-box");
        new TextBoxPage()
                .insertData()
                .checkOutputPermanentAddress("Санкт-Петербург, пр. Невский, д. 10");
    }

    @Test
    @Story("Длинное имя")
    @Severity(SeverityLevel.MINOR)
    @Tag("regression")
    @DisplayName("Очень длинное имя принимается формой")
    void longNameAccepted() {
        String longName = "A".repeat(200);
        openPage("text-box");
        new TextBoxPage()
                .insertDataWithName(longName)
                .checkOutputName(longName);
    }

    @Test
    @Story("Спецсимволы в имени")
    @Severity(SeverityLevel.MINOR)
    @Tag("regression")
    @DisplayName("Имя со спецсимволами принимается формой")
    void specialCharsInName() {
        String specialName = "O'Brien & Co. <Test>";
        openPage("text-box");
        new TextBoxPage()
                .insertDataWithName(specialName)
                .checkOutputName(specialName);
    }

    @Test
    @Story("Многострочный адрес")
    @Severity(SeverityLevel.MINOR)
    @Tag("regression")
    @DisplayName("Адрес с переносом строки корректно отображается")
    void multilineAddress() {
        String address = "Line 1\nLine 2\nLine 3";
        openPage("text-box");
        new TextBoxPage()
                .insertDataWithCurrentAddress(address)
                .checkOutputVisible();
    }
}