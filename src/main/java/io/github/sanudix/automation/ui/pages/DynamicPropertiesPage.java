package io.github.sanudix.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DynamicPropertiesPage {

    private static final Duration WAIT = Duration.ofSeconds(6);

    private final SelenideElement enableAfterBtn  = $("#enableAfter");
    private final SelenideElement colorChangeBtn  = $("#colorChange");
    private final SelenideElement visibleAfterBtn = $("#visibleAfter");

    @Step("Проверить: кнопка неактивна при загрузке")
    public DynamicPropertiesPage checkEnableButtonDisabled() {
        enableAfterBtn.shouldBe(visible).shouldBe(disabled);
        return this;
    }

    @Step("Проверить: кнопка становится активной")
    public DynamicPropertiesPage checkEnableButtonEnabled() {
        enableAfterBtn.shouldBe(enabled, WAIT);
        return this;
    }

    @Step("Проверить: кнопка не имеет класса text-danger")
    public DynamicPropertiesPage checkColorButtonNoClass() {
        colorChangeBtn.shouldBe(visible).shouldNotHave(cssClass("text-danger"));
        return this;
    }

    @Step("Проверить: кнопка получает класс text-danger")
    public DynamicPropertiesPage checkColorButtonHasClass() {
        colorChangeBtn.shouldHave(cssClass("text-danger"), WAIT);
        return this;
    }

    @Step("Проверить: кнопка не видна при загрузке")
    public DynamicPropertiesPage checkVisibleButtonHidden() {
        visibleAfterBtn.shouldNotBe(visible);
        return this;
    }

    @Step("Проверить: кнопка появляется")
    public DynamicPropertiesPage checkVisibleButtonAppears() {
        visibleAfterBtn.shouldBe(visible, WAIT);
        return this;
    }
}
