package io.github.sanudix.automation.mobile;

import io.github.sanudix.automation.base.BaseMobileTest;
import io.github.sanudix.automation.mobile.screens.CatalogScreen;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

@Feature("Пятёрочка")
@Story("Корзина")
@Tags({@Tag("Cart"), @Tag("Mobile")})
public class ShoppingCartTest extends BaseMobileTest {

    @Test
    @DisplayName("Добавить 3 товара в корзину и оформить заказ")
    @Severity(BLOCKER)
    public void addThreeProductsAndCheckout() {
        new CatalogScreen()
                .openCatalog()
                .clickSearch()

                .search("молоко")
                .addToCartWithQuantity(2, 1)

                .search("творог")
                .scrollDownTimes(2)
                .clickCardInRow(2)
                .addToCart(3)
                .goBack()

                .search("курица")
                .scrollDownTimes(2)
                .clickCardInRow(4)
                .addToCart(2)
                .openBasket()
                .scrollToPayAndClick();
    }
}