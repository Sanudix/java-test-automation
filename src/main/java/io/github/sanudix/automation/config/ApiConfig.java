package io.github.sanudix.automation.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:config/${env}.properties"
})
public interface ApiConfig extends Config {

    @Key("apiBaseUrl")
    @DefaultValue("https://reqres.in")
    String baseUrl();

    @Key("apiBasePath")
    @DefaultValue("/api")
    String basePath();

    @Key("apiKey")
    @DefaultValue("")
    String apiKey();
}
