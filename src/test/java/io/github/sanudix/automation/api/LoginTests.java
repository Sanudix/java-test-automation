package io.github.sanudix.automation.api;

import io.github.sanudix.automation.base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("reqres.in API")
@Feature("POST /login — авторизация")
@DisplayName("Тесты авторизации")
public class LoginTests extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешный логин возвращает token")
    void successfulLoginReturnsToken() {
        String token = given()
                .body(Map.of("email", "eve.holt@reqres.in", "password", "cityslicka"))
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        assertThat(token).isNotBlank();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Логин без пароля возвращает 400")
    void loginWithoutPasswordReturns400() {
        given()
                .body(Map.of("email", "eve.holt@reqres.in"))
                .when()
                .post("/login")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Логин без email возвращает 400")
    void loginWithoutEmailReturns400() {
        given()
                .body(Map.of("password", "cityslicka"))
                .when()
                .post("/login")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing email or username"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Логин с неверным паролем возвращает 400")
    void loginWithWrongPasswordReturns400() {
        given()
                .body(Map.of("email", "eve.holt@reqres.in", "password", "wrongpassword"))
                .when()
                .post("/login")
                .then()
                .statusCode(400);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Token имеет ненулевую длину")
    void tokenHasNonZeroLength() {
        String token = given()
                .body(Map.of("email", "eve.holt@reqres.in", "password", "cityslicka"))
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        assertThat(token).hasSizeGreaterThan(5);
    }

    @ParameterizedTest(name = "Невалидный логин email={0}, password={1}")
    @CsvSource({
            "unknown@reqres.in, wrongpass",
            "notexist@test.com, 12345",
            "bad-email, password"
    })
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Параметризованные невалидные комбинации возвращают 400")
    void invalidLoginCombinationsReturn400(String email, String password) {
        given()
                .body(Map.of("email", email, "password", password))
                .when()
                .post("/login")
                .then()
                .statusCode(400);
    }
}
