package io.github.sanudix.automation.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:config/${env}.properties"
})
public interface MobileConfig extends Config {

    @Key("mobilePlatform")
    @DefaultValue("emulator")
    String platform();

    @Key("appiumUrl")
    @DefaultValue("http://127.0.0.1:4723")
    String appiumUrl();

    @Key("androidDevice")
    @DefaultValue("Pixel 4")
    String androidDevice();

    @Key("androidVersion")
    @DefaultValue("11.0")
    String androidVersion();

    @Key("appPath")
    String appPath();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

    @Key("bsUser")
    String bsUser();

    @Key("bsKey")
    String bsKey();

    @Key("bsApp")
    String bsApp();

    @Key("buildName")
    @DefaultValue("local-build")
    String buildName();
}
