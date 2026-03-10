package io.github.sanudix.automation.mobile.screens;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CartScreen {

    @Step("Прокрутить до кнопки оплаты и нажать")
    public CartScreen scrollToPayAndClick() {
        $(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector()" +
                        ".resourceId(\"ru.pyaterochka.app.browser:id/vPayButton\"))"
        )).shouldBe(Condition.visible).click();
        return this;
    }
}