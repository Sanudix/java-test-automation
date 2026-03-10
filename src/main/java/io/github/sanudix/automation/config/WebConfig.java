package io.github.sanudix.automation.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:config/${env}.properties"
})
public interface WebConfig extends Config {

    @Key("webBrowserName")
    @DefaultValue("chrome")
    String browserName();

    @Key("webBrowserVersion")
    @DefaultValue("")
    String browserVersion();

    @Key("webBrowserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("webBaseUrl")
    @DefaultValue("https://demoqa.com")
    String baseUrl();

    @Key("webIsRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("webRemoteUrl")
    String remoteUrl();

    @Key("webPageLoadTimeout")
    @DefaultValue("10000")
    long pageLoadTimeout();

    @Key("webTimeout")
    @DefaultValue("10000")
    long timeout();

    @Key("webIsHeadless")
    @DefaultValue("false")
    boolean isHeadless();
}
