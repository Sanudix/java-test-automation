package io.github.sanudix.automation.api;

import io.github.sanudix.automation.api.models.RegisterResponse;
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
@Feature("POST /register — регистрация")
@DisplayName("Тесты регистрации")
public class RegisterTests extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешная регистрация возвращает id и token")
    void successfulRegisterReturnsIdAndToken() {
        RegisterResponse response = given()
                .body(Map.of("email", "eve.holt@reqres.in", "password", "pistol"))
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .extract().as(RegisterResponse.class);

        assertThat(response.getId()).isPositive();
        assertThat(response.getToken()).isNotBlank();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Регистрация без пароля возвращает 400")
    void registerWithoutPasswordReturns400() {
        given()
                .body(Map.of("email", "eve.holt@reqres.in"))
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Регистрация без email возвращает 400")
    void registerWithoutEmailReturns400() {
        given()
                .body(Map.of("password", "pistol"))
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing email or username"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Незарегистрированный email возвращает 400")
    void unregisteredEmailReturns400() {
        given()
                .body(Map.of("email", "unknown@unknown.com", "password", "password123"))
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .body("error", equalTo("Note: Only defined users succeed registration"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Token не пустой при успешной регистрации")
    void tokenIsNotEmptyOnSuccess() {
        RegisterResponse response = given()
                .body(Map.of("email", "eve.holt@reqres.in", "password", "pistol"))
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .extract().as(RegisterResponse.class);

        assertThat(response.getToken()).hasSizeGreaterThan(5);
    }

    @ParameterizedTest(name = "Невалидная регистрация email={0}")
    @CsvSource({
            "nopassword@reqres.in, ''",
            "invalid-email, password123"
    })
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Параметризованные невалидные комбинации возвращают 400")
    void invalidRegisterCombinationsReturn400(String email, String password) {
        given()
                .body(Map.of("email", email, "password", password))
                .when()
                .post("/register")
                .then()
                .statusCode(400);
    }
}
