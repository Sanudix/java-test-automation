package io.github.sanudix.automation.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.sanudix.automation.mobile.driver.LocalAndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

@Tag("mobile")
public abstract class BaseMobileTest {

    @BeforeAll
    static void setupAll() {
        Configuration.browser = LocalAndroidDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 15000;
    }

    @BeforeEach
    void setUp() {
        Selenide.open();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}