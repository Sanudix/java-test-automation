package io.github.sanudix.automation.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.sanudix.automation.config.ConfigProvider;
import io.github.sanudix.automation.config.WebConfig;
import io.github.sanudix.automation.ui.pages.MainPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

@Tag("ui")
public abstract class BaseWebTest {

    private static final WebConfig config = ConfigProvider.webConfig();

    @BeforeAll
    static void setupAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.browser = config.browserName();
        Configuration.browserVersion = config.browserVersion();
        Configuration.browserSize = config.browserSize();
        Configuration.baseUrl = config.baseUrl();
        Configuration.pageLoadTimeout = config.pageLoadTimeout();
        Configuration.timeout = config.timeout();
        Configuration.headless = config.isHeadless();

        if (config.isRemote()) {
            Configuration.remote = config.remoteUrl();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options",
                    Map.of(
                            "enableVNC", true,
                            "enableVideo", true
                    )
            );
            Configuration.browserCapabilities = capabilities;
        }
    }

    protected void openPage(String path) {
        Selenide.open("/" + path);
    }

    protected MainPage openMainPage() {
        return new MainPage();
    }

    @AfterEach
    void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
