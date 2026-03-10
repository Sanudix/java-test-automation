package io.github.sanudix.automation.base;

import io.github.sanudix.automation.config.ApiConfig;
import io.github.sanudix.automation.config.ConfigProvider;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;

@Tag("api")
public abstract class BaseApiTest {

    private static final ApiConfig config = ConfigProvider.apiConfig();

    static {
        RestAssured.baseURI = config.baseUrl();
        RestAssured.basePath = config.basePath();

        RestAssured.filters(new AllureRestAssured());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("x-api-key", config.apiKey())
                .addHeader("User-Agent", "ReqResTestClient/1.0")
                .log(LogDetail.ALL)
                .build();
    }
}