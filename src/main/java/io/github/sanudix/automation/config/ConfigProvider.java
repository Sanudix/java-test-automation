package io.github.sanudix.automation.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {

    private static WebConfig webConfig;
    private static ApiConfig apiConfig;
    private static MobileConfig mobileConfig;

    static {
        ConfigFactory.setProperty("env", System.getProperty("env", "local"));
    }

    public static WebConfig webConfig() {
        if (webConfig == null) {
            webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
        }
        return webConfig;
    }

    public static ApiConfig apiConfig() {
        if (apiConfig == null) {
            apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());
        }
        return apiConfig;
    }

    public static MobileConfig mobileConfig() {
        if (mobileConfig == null) {
            mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());
        }
        return mobileConfig;
    }
}
