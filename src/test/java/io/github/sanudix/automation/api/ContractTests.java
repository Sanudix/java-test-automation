package io.github.sanudix.automation.api;

import io.github.sanudix.automation.base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("reqres.in API")
@Feature("Контрактные тесты — заголовки, время ответа, структура")
@DisplayName("Контрактные и структурные тесты")
public class ContractTests extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("GET /users содержит Content-Type: application/json")
    void getUsersReturnsJsonContentType() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .contentType("application/json");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("GET /users/{id} содержит Content-Type: application/json")
    void getSingleUserReturnsJsonContentType() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .contentType("application/json");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("GET /users отвечает быстрее 2000ms")
    void getUsersResponseTimeIsUnder2000ms() {
        Response response = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().response();

        assertThat(response.getTime()).isLessThan(2000L);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("GET /users/{id} отвечает быстрее 2000ms")
    void getSingleUserResponseTimeIsUnder2000ms() {
        Response response = given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .extract().response();

        assertThat(response.getTime()).isLessThan(2000L);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("GET /users?delay=1 отвечает дольше 1000ms и возвращает 200")
    void delayedResponseReturns200WithData() {
        Response response = given()
                .queryParam("delay", 1)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().response();

        assertThat(response.getTime()).isGreaterThan(1000L);
        assertThat(response.jsonPath().getList("data")).isNotEmpty();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("GET /users ответ содержит поля page, total, total_pages, data")
    void getUsersResponseHasTopLevelFields() {
        String responseBody = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().asString();

        assertThat(responseBody).contains("page");
        assertThat(responseBody).contains("total");
        assertThat(responseBody).contains("total_pages");
        assertThat(responseBody).contains("data");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("GET /users/{id} ответ содержит обёртку data и support")
    void getSingleUserResponseHasDataAndSupport() {
        String responseBody = given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .extract().asString();

        assertThat(responseBody).contains("data");
        assertThat(responseBody).contains("support");
    }
}
