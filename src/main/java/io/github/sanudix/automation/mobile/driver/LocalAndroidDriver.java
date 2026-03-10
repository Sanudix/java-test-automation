package io.github.sanudix.automation.mobile.driver;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

@SuppressWarnings("unused")
public class LocalAndroidDriver implements WebDriverProvider {

    private static EmulatorConfig loadConfig() {
        Properties props = new Properties();
        try (InputStream is = LocalAndroidDriver.class
                .getClassLoader()
                .getResourceAsStream("config/emulator.properties")) {
            if (is == null) {
                throw new RuntimeException("emulator.properties не найден в classpath");
            }
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить emulator.properties", e);
        }
        props.putAll(System.getProperties());
        return ConfigFactory.create(EmulatorConfig.class, props);
    }

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        EmulatorConfig config = loadConfig();

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(config.platformName())
                .setDeviceName(config.deviceName())
                .setAutomationName("UiAutomator2")
                .amend("appium:appPackage", config.appPackage())
                .amend("appium:appActivity", config.appActivity())
                .amend("appium:noReset", true)
                .amend("appium:forceAppLaunch", true)
                .amend("appium:uiautomator2ServerLaunchTimeout", 60000)
                .amend("appium:newCommandTimeout", 120)
                .amend("appium:disableWindowAnimation", true);

        try {
            return new AndroidDriver(new URL(config.remoteURL()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Неверный URL Appium сервера: " + config.remoteURL(), e);
        }
    }
}