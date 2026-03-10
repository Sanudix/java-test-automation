package io.github.sanudix.automation.ui;

import io.github.sanudix.automation.base.BaseWebTest;
import io.github.sanudix.automation.ui.pages.RadioButtonPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Epic("Elements")
@Feature("Radio Button")
@DisplayName("Elements → Radio Button")
public class RadioButtonTest extends BaseWebTest {

    @ParameterizedTest(name = "Выбор {0} → результат \"{1}\"")
    @CsvSource({
            "Yes,        Yes",
            "Impressive, Impressive"
    })
    @Story("Выбор радио-кнопки")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    void selectRadioButton(String buttonLabel, String expectedResult) {
        openPage("radio-button");
        RadioButtonPage page = new RadioButtonPage();

        switch (buttonLabel) {
            case "Yes" -> page.clickYes();
            case "Impressive" -> page.clickImpressive();
        }

        page.checkResultText(expectedResult);
    }

    @Test
    @Story("Кнопка No задизейблена")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Кнопка No задизейблена")
    void noButtonIsDisabled() {
        openPage("radio-button");
        new RadioButtonPage()
                .checkNoButtonDisabled();
    }

    @Test
    @Story("Переключение радио-кнопок")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Переключение с Yes на Impressive")
    void switchFromYesToImpressive() {
        openPage("radio-button");
        new RadioButtonPage()
                .clickYes()
                .clickImpressive()
                .checkResultText("Impressive");
    }
}
