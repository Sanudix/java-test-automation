package io.github.sanudix.automation.mobile.screens;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogScreen {

    private final SelenideElement catalogTab =
            $(AppiumBy.accessibilityId("Каталог"));

    private final SelenideElement searchButton =
            $(AppiumBy.id("ru.pyaterochka.app.browser:id/vSearch"));

    private final SelenideElement searchField =
            $(AppiumBy.id("ru.pyaterochka.app.browser:id/vSearchField"));

    @Step("Открыть каталог")
    public CatalogScreen openCatalog() {
        catalogTab.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Нажать на поиск")
    public CatalogScreen clickSearch() {
        searchButton.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Искать: {text}")
    public CatalogScreen search(String text) {
        searchField.shouldBe(Condition.visible).clear();
        searchField.sendKeys(text);
        hideKeyboard();
        return this;
    }

    @Step("Добавить в корзину товар #{position}")
    public CatalogScreen addToCartByPosition(int position) {
        String selector = "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().text(\"В корзину\").instance(" + (position - 1) + "))";
        $(AppiumBy.androidUIAutomator(selector)).shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Добавить в корзину товар #{position}, количество: {quantity}")
    public CatalogScreen addToCartWithQuantity(int position, int quantity) {
        addToCartByPosition(position);

        SelenideElement plusBtn = $(AppiumBy.id(
                "ru.pyaterochka.app.browser:id/vPlusImageBtn"
        ));
        for (int i = 1; i < quantity; i++) {
            plusBtn.shouldBe(Condition.visible).click();
        }
        return this;
    }

    @Step("Прокрутить вниз")
    public CatalogScreen scrollDown() {
        AndroidDriver driver = (AndroidDriver) com.codeborne.selenide.WebDriverRunner.getWebDriver();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"
        ));
        return this;
    }

    @Step("Прокрутить вниз {times} раз")
    public CatalogScreen scrollDownTimes(int times) {
        for (int i = 0; i < times; i++) {
            scrollDown();
        }
        return this;
    }

    @Step("Выбрать карточку товара #{positionInRow}")
    public ProductScreen clickCardInRow(int positionInRow) {
        ElementsCollection cards = $$(AppiumBy.id(
                "ru.pyaterochka.app.browser:id/vMainView"
        ));
        cards.get(positionInRow - 1).shouldBe(Condition.visible).click();
        return new ProductScreen();
    }

    private void hideKeyboard() {
        try {
            AndroidDriver driver = (AndroidDriver) com.codeborne.selenide.WebDriverRunner.getWebDriver();
            driver.hideKeyboard();
        } catch (Exception ignored) {
        }
    }
}