package io.github.sanudix.automation.mobile.screens;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class ProductScreen {

    private final SelenideElement addToCartBtn =
            $(AppiumBy.id("ru.pyaterochka.app.browser:id/vProductAddBtnContainer"));

    private final SelenideElement plusBtn =
            $(AppiumBy.id("ru.pyaterochka.app.browser:id/vPlusImageBtn"));

    private final SelenideElement backBtn =
            $(AppiumBy.id("ru.pyaterochka.app.browser:id/vBack"));

    private final SelenideElement basketContainer =
            $(AppiumBy.id("ru.pyaterochka.app.browser:id/pinned_cart_container"));

    @Step("Добавить в корзину {quantity} шт")
    public ProductScreen addToCart(int quantity) {
        addToCartBtn.shouldBe(Condition.visible).click();

        for (int i = 1; i < quantity; i++) {
            plusBtn.shouldBe(Condition.visible).click();
        }
        return this;
    }

    @Step("Вернуться назад")
    public CatalogScreen goBack() {
        backBtn.shouldBe(Condition.visible).click();
        return new CatalogScreen();
    }

    @Step("Открыть корзину")
    public CartScreen openBasket() {
        AndroidDriver driver = (AndroidDriver) com.codeborne.selenide.WebDriverRunner.getWebDriver();

        basketContainer.shouldBe(Condition.visible);
        int x = basketContainer.getLocation().getX()
                + (int) (basketContainer.getSize().getWidth() * 0.85);
        int y = basketContainer.getLocation().getY()
                + basketContainer.getSize().getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 0);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerMove(Duration.ofMillis(50), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(tap));

        return new CartScreen();
    }
}