package io.github.sanudix.automation.api;

import io.github.sanudix.automation.api.models.UnknownResource;
import io.github.sanudix.automation.api.models.UnknownResourceListResponse;
import io.github.sanudix.automation.base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("reqres.in API")
@Feature("GET /unknown — ресурсы")
@DisplayName("Тесты ресурсов /unknown")
public class UnknownResourceTests extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Список ресурсов содержит id, name, year, color, pantone_value")
    void resourceListHasRequiredFields() {
        UnknownResourceListResponse response = given()
                .when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .extract().as(UnknownResourceListResponse.class);

        assertThat(response.getData()).isNotEmpty();
        for (UnknownResource r : response.getData()) {
            assertThat(r.getId()).isPositive();
            assertThat(r.getName()).isNotBlank();
            assertThat(r.getYear()).isPositive();
            assertThat(r.getColor()).isNotBlank();
            assertThat(r.getPantoneValue()).isNotBlank();
        }
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("color соответствует формату HEX (#XXXXXX)")
    void colorIsValidHex() {
        UnknownResourceListResponse response = given()
                .when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .extract().as(UnknownResourceListResponse.class);

        assertThat(response.getData())
                .allMatch(r -> r.getColor().matches("^#[0-9A-Fa-f]{6}$"));
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("year — число больше 1990")
    void yearIsGreaterThan1990() {
        UnknownResourceListResponse response = given()
                .when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .extract().as(UnknownResourceListResponse.class);

        assertThat(response.getData())
                .allMatch(r -> r.getYear() > 1990);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Несуществующий ресурс /unknown/23 возвращает 404")
    void nonExistentResourceReturns404() {
        given()
                .when()
                .get("/unknown/23")
                .then()
                .statusCode(404);
    }

    @ParameterizedTest(name = "Ресурс {0} существует и возвращает 200")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Все ресурсы 1-6 существуют")
    void allResourcesFrom1to6Exist(int resourceId) {
        UnknownResource resource = given()
                .when()
                .get("/unknown/" + resourceId)
                .then()
                .statusCode(200)
                .extract().jsonPath().getObject("data", UnknownResource.class);

        assertThat(resource.getId()).isEqualTo(resourceId);
        assertThat(resource.getName()).isNotBlank();
    }
}
