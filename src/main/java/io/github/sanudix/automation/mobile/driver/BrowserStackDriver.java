package io.github.sanudix.automation.mobile.driver;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.github.sanudix.automation.config.ConfigProvider;
import io.github.sanudix.automation.config.MobileConfig;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MobileConfig config = ConfigProvider.mobileConfig();

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName(config.androidDevice())
                .setPlatformVersion(config.androidVersion())
                .setAutomationName("UiAutomator2")
                .setApp(config.bsApp())
                .amend("appium:appPackage", config.appPackage())
                .amend("appium:appActivity", config.appActivity())
                .amend("appium:noReset", true)
                .amend("bstack:options", java.util.Map.of(
                        "userName", config.bsUser(),
                        "accessKey", config.bsKey(),
                        "buildName", config.buildName(),
                        "sessionName", "Mobile Test"
                ));

        try {
            System.out.println("=== BS DEBUG ===");
            System.out.println("bsUser: " + (config.bsUser() != null ? "SET" : "NULL"));
            System.out.println("bsKey: " + (config.bsKey() != null ? "SET" : "NULL"));
            System.out.println("bsApp: " + config.bsApp());
            System.out.println("device: " + config.androidDevice());
            System.out.println("platform: " + config.platform());
            System.out.println("================");
            return new AndroidDriver(
                    new URL("http://hub.browserstack.com/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("BrowserStack URL error", e);
        }
    }
}