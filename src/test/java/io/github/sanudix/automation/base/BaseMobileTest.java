package io.github.sanudix.automation.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.sanudix.automation.config.ConfigProvider;
import io.github.sanudix.automation.config.MobileConfig;
import io.github.sanudix.automation.mobile.driver.BrowserStackDriver;
import io.github.sanudix.automation.mobile.driver.LocalAndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

@Tag("mobile")
public abstract class BaseMobileTest {

    @BeforeAll
    static void setupAll() {
        MobileConfig config = ConfigProvider.mobileConfig();

        System.out.println("=== MOBILE CONFIG DEBUG ===");
        System.out.println("platform: [" + config.platform() + "]");
        System.out.println("env system prop: [" + System.getProperty("env") + "]");
        System.out.println("bsApp from config: [" + config.bsApp() + "]");
        System.out.println("androidDevice from config: [" + config.androidDevice() + "]");
        System.out.println("appPackage from config: [" + config.appPackage() + "]");
        System.out.println("===========================");

        if ("browserstack".equals(config.platform())) {
            Configuration.browser = BrowserStackDriver.class.getName();
        } else {
            Configuration.browser = LocalAndroidDriver.class.getName();
        }

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