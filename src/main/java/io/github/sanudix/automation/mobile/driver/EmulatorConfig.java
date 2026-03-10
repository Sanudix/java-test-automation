package io.github.sanudix.automation.mobile.driver;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:config/emulator.properties"
})
public interface EmulatorConfig extends Config {

    @Key("platformName")
    @DefaultValue("Android")
    String platformName();

    @Key("deviceName")
    @DefaultValue("Pixel 4")
    String deviceName();

    @Key("remoteURL")
    @DefaultValue("http://127.0.0.1:4723")
    String remoteURL();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();
}